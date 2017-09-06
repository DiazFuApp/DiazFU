using DiazFu.App_Code.Entidades;
using DiazFu.App_Code.Utilerias;
using System;
using System.Data;
using System.Web.UI.WebControls;

namespace DiazFu.Modules.Administracion.Clientes
{
    public partial class Listado : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarGrid();
            }
        }

        /// <summary>
        /// Método para cargar el grid
        /// </summary>
        public void CargarGrid()
        {
            App_Code.Entidades.Clientes Clientes = new App_Code.Entidades.Clientes();
            gvClientes.DataSource = Clientes.ConsultarTodo();
            gvClientes.DataBind();
        }

        /// <summary>
        /// Evento para eliminar el cliente
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void bEliminar_Click(object sender, EventArgs e)
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            Button boton = (Button)sender;
            int id = int.Parse(boton.CommandArgument);
            App_Code.Entidades.Clientes Cliente = new App_Code.Entidades.Clientes
            {
                Id = id,
                IdUsuario = IDUsuarioActual
            };
            Cliente.ConsultarID();
            Cliente.IdEstatus = 2;
            Cliente.Actualizar();
            Literal literal = (Literal)Master.FindControl("lAlerta");
            if (Cliente.Id != 0)
            {
                //BAJA DE REDES SOCIALES DEL CLIENTE
                RedesSociales RedesCiente = new RedesSociales
                {
                    IdActor = Cliente.Id,
                    IdTipoActor = 3
                };
                using (DataSet ConsultaRedesCliente = RedesCiente.ConsultarTodo())
                {
                    foreach (DataRow FilaRedCliente in ConsultaRedesCliente.Tables[0].Rows)
                    {
                        RedesSociales RedSocial = new RedesSociales
                        {
                            Id = int.Parse(FilaRedCliente["Id"].ToString()),
                            IdTipoActor = 3
                        };
                        RedSocial.ConsultarID();
                        RedSocial.IdEstatus = 2;
                        RedSocial.Actualizar();
                    }
                }
                //BAJA DE DOCUMENTOS DEL CLIENTE
                Documentos Documentos = new Documentos()
                {
                    IdActor = Cliente.Id,
                    IdTipoActor = 3
                };
                using (DataSet ConsultaDocumentosCliente = Documentos.ConsultarTodo())
                {
                    foreach (DataRow FilaDocumentoCliente in ConsultaDocumentosCliente.Tables[0].Rows)
                    {
                        Documentos Documento = new Documentos()
                        {
                            Id = int.Parse(FilaDocumentoCliente["Id"].ToString()),
                            IdTipoActor = 3
                        };
                        Documento.ConsultarID();
                        Documento.IdEstatus = 2;
                        Documento.Actualizar();
                    }
                }
                literal.Text = Herramientas.Alerta("Operación existosa!", "Cliente eliminado correctamente.", 3);
            }
            else
            {
                literal.Text = Herramientas.Alerta("Ocurrió un error!", "No ha sido posible eliminar al cliente.", 4);
            }
            CargarGrid();
        }
    }
}