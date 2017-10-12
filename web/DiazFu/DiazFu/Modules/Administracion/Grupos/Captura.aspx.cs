using DiazFu.App_Code.Utilerias;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DiazFu.Modules.Administracion.Grupos
{
    public partial class Captura : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarControles();
                if (Request.QueryString["id"] != null)
                {
                    ConsultarGrupo();
                }
            }
        }

        /// <summary>
        /// MÉTODO PARA CARGAR LOS CONTROLES DEL FORMULARIO
        /// </summary>
        private void CargarControles()
        {
            App_Code.Entidades.Clientes Clientes = new App_Code.Entidades.Clientes();
            using (DataSet Consulta = Clientes.ConsultarSinGrupo())
            {
                ddl_Clientes.DataSource = Consulta;
                ddl_Clientes.DataTextField = "Nombre";
                ddl_Clientes.DataValueField = "id";
                ddl_Clientes.DataBind();
            }
            App_Code.Entidades.Promotores Promotores = new App_Code.Entidades.Promotores();
            using (DataSet Consulta = Promotores.ConsultarTodo())
            {
                ddl_Promotor.DataSource = Consulta;
                ddl_Promotor.DataTextField = "Nombre";
                ddl_Promotor.DataValueField = "id";
                ddl_Promotor.DataBind();
            }
            gvClientes.DataSource = Grid_Clientes_DataSource;
            gvClientes.DataBind();

            //ELIMINAR CLIENTES INSERTADOS DEL COMBO
            foreach (DataRow Fila in Grid_Clientes_DataSource.Tables["ASP"].Rows)
            {
                ListItem item = ddl_Clientes.Items.FindByValue(Fila["IdCliente"].ToString());
                ddl_Clientes.Items.Remove(item);
            }
            //AGREGAR ATRIBUTO DISABLED
            if (ddl_Clientes.Items.Count == 0)
            {
                ddl_Clientes.Attributes.Add("disabled", "");
                b_Agregar_Cliente.Attributes.Add("disabled", "");
            }
        }

        /// <summary>
        /// MÉTODO PARA CARGAR LOS DATOS DEL GRUPO
        /// </summary>
        private void ConsultarGrupo()
        {
            int ID = int.Parse(Request.QueryString["id"].ToString());
            App_Code.Entidades.Grupos Grupos = new App_Code.Entidades.Grupos
            {
                Id = ID
            };
            Grupos.ConsultarID();
            tb_Nombre.Text = Grupos.Nombre;
            ddl_Promotor.SelectedValue = Grupos.IdPromotor.ToString();
        }

        /// <summary>
        /// PROPIEDAD DEL GRID
        /// </summary>
        DataSet Grid_Clientes_DataSource
        {
            get
            {
                DataSet Consulta = new DataSet();
                if (ViewState["Clientes"] == null)
                {
                    App_Code.Entidades.IntegrantesGrupos Integrantes = new App_Code.Entidades.IntegrantesGrupos
                    {
                        IdGrupo = 0
                    };
                    if (Request.QueryString["id"] != null)
                    {
                        Integrantes.IdGrupo = int.Parse(Request.QueryString["id"].ToString());
                    }
                    DataSet BD = Integrantes.ConsultarTodo();
                    BD.Tables[0].TableName = "ASP";
                    DataTable dt = new DataTable();
                    dt = BD.Tables["ASP"].Copy();
                    dt.TableName = "Code";
                    BD.Tables.Add(dt);
                    ViewState["Clientes"] = BD;
                }
                return (DataSet)ViewState["Clientes"];
            }
            set
            {
                ViewState["Clientes"] = value;
            }
        }

        /// <summary>
        /// MÉTODO PARA CARGAR EL GRID
        /// </summary>
        public void CargarGrid()
        {
            gvClientes.DataSource = Grid_Clientes_DataSource.Tables["ASP"];
            gvClientes.DataBind();
            ScriptManager.RegisterStartupScript(up_Clientes, up_Clientes.GetType(), Guid.NewGuid().ToString(), "RecargaEstiloGrid();", true);
        }

        //EVENTO PARA SELECCIONAR EL RADIO DEL CLIENTE RESPONSABLE
        protected void gvClientes_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            DataRowView drv;
            if (e.Row.RowType == DataControlRowType.DataRow && e.Row.DataItem != null)
            {
                if (Request.QueryString["id"] != null)
                {
                    int ID = int.Parse(Request.QueryString["id"].ToString());
                    App_Code.Entidades.Grupos Grupos = new App_Code.Entidades.Grupos
                    {
                        Id = ID
                    };
                    Grupos.ConsultarID();
                    drv = (DataRowView)e.Row.DataItem;
                    if (drv["IdCliente"].ToString() == Grupos.IdClienteResponsable.ToString())
                    {
                        RadioButton rb = (RadioButton)e.Row.FindControl("rbReponsable");
                        rb.Checked = true;
                    } 
                }
            }
        }

        /// <summary>
        /// EVENTO PARA GUARDAR EL GRUPO
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void b_Crear_Click(object sender, EventArgs e)
        {
            //int Contador = (from cliente in Grid_Clientes_DataSource.Tables[0].AsEnumerable() where cliente.Field<int>("IdEstatus") != 3 select cliente).Count();
            int Contador = 5;
            if (Contador > 4 && Contador < 11)
            {
                if (Request.QueryString["id"] == null)
                {
                    Crear();
                }
                else
                {
                    Actualizar();
                }
            }
            else
            {
                Literal literal = (Literal)Master.FindControl("lAlerta");
                literal.Text = Herramientas.Alerta("Oops, ocurrió un error!", "Por favor, selecciona los clientes que serán parte de este grupo", 4);
            }
        }

        /// <summary>
        /// MÉTODO PARA CREAR EL GRUPO
        /// </summary>
        private void Crear()
        {
            try
            {
                int IDUsuarioActual = 0;
                int.TryParse(((App_Code.Entidades.Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);

                #region Responsable
                int IDResponsable = 0;
                foreach (GridViewRow Fila in gvClientes.Rows)
                {
                    if (((RadioButton)Fila.FindControl("rbReponsable")).Checked)
                    {
                        IDResponsable = int.Parse(Grid_Clientes_DataSource.Tables["ASP"].Rows[Fila.DataItemIndex]["IdCliente"].ToString());
                    }
                }
                if (IDResponsable == 0)
                {
                    throw new Exception("Por favor, seleccione un cliente responsable del grupo");
                }
                #endregion

                App_Code.Entidades.Grupos Grupo = new App_Code.Entidades.Grupos
                {
                    Nombre = tb_Nombre.Text,
                    IdClienteResponsable = IDResponsable,
                    IdPromotor = int.Parse(ddl_Promotor.SelectedValue),
                    IdUsuario = IDUsuarioActual
                };
                Grupo.Agregar();

                if (Grupo.Id != null)
                {
                    foreach (DataRow Fila in Grid_Clientes_DataSource.Tables["Code"].Rows)
                    {
                        if (Fila["IdEstatus"].ToString() == "2")
                        {
                            App_Code.Entidades.IntegrantesGrupos Integrante = new App_Code.Entidades.IntegrantesGrupos
                            {
                                IdCliente = int.Parse(Fila["IdCliente"].ToString()),
                                IdGrupo = Grupo.Id,
                                IdUsuario = IDUsuarioActual
                            };
                            Integrante.Agregar();
                        }
                    }
                    Session["Alerta"] = Herramientas.Alerta("Operación existosa!", "Grupo actualizado correctamente.", 3);
                    Response.Redirect("Listado.aspx");
                }
                else
                {
                    Literal literal = (Literal)Master.FindControl("lAlerta");
                    literal.Text = Herramientas.Alerta("Oops, ocurrió un error!", "Se produjo un error al intentar crear el grupo, por favor reintenta", 4);
                }
            }
            catch (Exception ex)
            {
                Literal literal = (Literal)Master.FindControl("lAlerta");
                literal.Text = Herramientas.Alerta("Oops, ocurrió un error!", ex.Message, 4);
            }
        }

        /// <summary>
        /// MÉTODO PARA ACTUALIZAR EL GRUPO
        /// </summary>
        private void Actualizar()
        {
            try
            {
                int IDUsuarioActual = 0;
                int.TryParse(((App_Code.Entidades.Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
                int ID = int.Parse(Request.QueryString["id"].ToString());

                #region Responsable
                int IDResponsable = 0;
                foreach (GridViewRow Fila in gvClientes.Rows)
                {
                    if (((RadioButton)Fila.FindControl("rbReponsable")).Checked)
                    {
                        IDResponsable = int.Parse(Grid_Clientes_DataSource.Tables["ASP"].Rows[Fila.DataItemIndex]["IdCliente"].ToString());
                    }
                }
                if (IDResponsable == 0)
                {
                    throw new Exception("Por favor, seleccione un cliente responsable del grupo");
                }
                #endregion

                App_Code.Entidades.Grupos Grupo = new App_Code.Entidades.Grupos
                {
                    Id = ID
                };
                Grupo.ConsultarID();
                Grupo.Nombre = tb_Nombre.Text;
                Grupo.IdClienteResponsable = IDResponsable;
                Grupo.IdPromotor = int.Parse(ddl_Promotor.SelectedValue);
                Grupo.IdUsuario = IDUsuarioActual;
                Grupo.Actualizar();

                if (Grupo.Id != null)
                {
                    foreach (DataRow Fila in Grid_Clientes_DataSource.Tables["Code"].Rows)
                    {
                        if (Fila["IdEstatus"].ToString() == "2")
                        {
                            App_Code.Entidades.IntegrantesGrupos Integrante = new App_Code.Entidades.IntegrantesGrupos
                            {
                                IdCliente = int.Parse(Fila["IdCliente"].ToString()),
                                IdGrupo = Grupo.Id,
                                IdUsuario = IDUsuarioActual
                            };
                            Integrante.ConsultarID();
                            if (Integrante.Id == null)
                            {
                                Integrante.Agregar();
                            }
                            else
                            {
                                Integrante.IdEstatus = 1;
                                Integrante.Actualizar();
                            }
                        }
                        else if (Fila["IdEstatus"].ToString() == "3")
                        {
                            App_Code.Entidades.IntegrantesGrupos Integrante = new App_Code.Entidades.IntegrantesGrupos
                            {
                                IdCliente = int.Parse(Fila["IdCliente"].ToString()),
                                IdGrupo = Grupo.Id,
                                IdUsuario = IDUsuarioActual
                            };
                            Integrante.ConsultarID();
                            Integrante.IdEstatus = 2;
                            Integrante.Actualizar();
                        }
                    }
                    Session["Alerta"] = Herramientas.Alerta("Operación existosa!", "Grupo actualizado correctamente.", 3);
                    Response.Redirect("Listado.aspx");
                }
                else
                {
                    Literal literal = (Literal)Master.FindControl("lAlerta");
                    literal.Text = Herramientas.Alerta("Oops, ocurrió un error!", "Se produjo un error al intentar actualizar el grupo, por favor reintenta", 4);
                }
            }
            catch (Exception ex)
            {
                Literal literal = (Literal)Master.FindControl("lAlerta");
                literal.Text = Herramientas.Alerta("Oops, ocurrió un error!", ex.Message, 4);
            }
        }

        /// <summary>
        /// EVENTO PARA AGREGAR EL CLIENTE AL LISTADO
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void b_Agregar_Cliente_Click(object sender, EventArgs e)
        {
            DataSet Clientes = Grid_Clientes_DataSource;
            App_Code.Entidades.Clientes Cliente = new App_Code.Entidades.Clientes
            {
                Id = int.Parse(ddl_Clientes.SelectedValue)
            };
            Cliente.ConsultarID();
            if (Cliente.Id != null)
            {
                Clientes.Tables["ASP"].Rows.Add("0", "0", "", ddl_Clientes.SelectedValue, ddl_Clientes.SelectedItem.Text, 1, 1, 0, DateTime.Now, 0, DateTime.Now);

                IEnumerable<DataRow> resultsCode = from Fila in Clientes.Tables["Code"].AsEnumerable() where Fila.Field<int>("IdCliente") == int.Parse(ddl_Clientes.SelectedValue) select Fila;
                if (resultsCode.Count() == 0)
                {
                    Clientes.Tables["Code"].Rows.Add("0", "0", "", ddl_Clientes.SelectedValue, ddl_Clientes.SelectedItem.Text, 2, "", 0, DateTime.Now, 0, DateTime.Now);
                }
                else
                {
                    int Index = Clientes.Tables["Code"].Rows.IndexOf(resultsCode.First<DataRow>());
                    Clientes.Tables["Code"].Rows[Index]["IdEstatus"] = 2;
                }

                ddl_Clientes.Items.Remove(ddl_Clientes.SelectedItem);
                //AGREGAR ATRIBUTO DISABLED
                if (ddl_Clientes.Items.Count == 0)
                {
                    ddl_Clientes.Attributes.Add("disabled", "");
                    b_Agregar_Cliente.Attributes.Add("disabled", "");
                }
                Grid_Clientes_DataSource = Clientes;
                CargarGrid();
            }
        }

        /// <summary>
        /// EVENTO PARA ELIMINAR EL REGISTRO DEL CLIENTE
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void bEliminar_Click(object sender, EventArgs e)
        {
            Button boton = (Button)sender;
            int id = int.Parse(boton.CommandArgument);
            DataSet Clientes = Grid_Clientes_DataSource;

            IEnumerable<DataRow> resultsASP = from Fila in Clientes.Tables["ASP"].AsEnumerable() where Fila.Field<int>("IdCliente") == id select Fila;
            IEnumerable<DataRow> resultsCode = from Fila in Clientes.Tables["Code"].AsEnumerable() where Fila.Field<int>("IdCliente") == id select Fila;

            //AGREGAR AL COMBO
            ListItem ClienteItem = new ListItem(resultsASP.First<DataRow>().ItemArray[4].ToString(), resultsASP.First<DataRow>().ItemArray[3].ToString());
            ddl_Clientes.Items.Add(ClienteItem);
            //REMOVER ATRIBUTO DISABLED
            ddl_Clientes.Attributes.Remove("disabled");
            b_Agregar_Cliente.Attributes.Remove("disabled");

            //ELIMINAR DEL DS
            int Index = Clientes.Tables["Code"].Rows.IndexOf(resultsCode.First<DataRow>());
            Clientes.Tables["Code"].Rows[Index]["IdEstatus"] = 3;
            Clientes.Tables["ASP"].Rows.Remove(resultsASP.First<DataRow>());
            Clientes.AcceptChanges();
            Grid_Clientes_DataSource = Clientes;

            CargarGrid();
        }
    }
}