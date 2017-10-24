using DiazFu.App_Code.Entidades;
using DiazFu.App_Code.Utilerias;
using System;
using System.Data;

namespace DiazFu.Modules.Actividades
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
                    ConsultarActividad();
                }
            }
        }

        /// <summary>
        /// MÉTODO PARA CARGAR LOS CONTROLES DEL FORMULARIO
        /// </summary>
        private void CargarControles()
        {
            App_Code.Entidades.Promotores Promotores = new App_Code.Entidades.Promotores();
            using (DataSet Consulta = Promotores.ConsultarTodo())
            {
                ddl_Promotor.DataSource = Consulta;
                ddl_Promotor.DataTextField = "Nombre";
                ddl_Promotor.DataValueField = "id";
                ddl_Promotor.DataBind();
            }
        }

        /// <summary>
        /// MÉTODO PARA CONSULTAR LA ACTIVIDAD
        /// </summary>
        private void ConsultarActividad()
        {
            int ID = int.Parse(Request.QueryString["id"].ToString());
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            App_Code.Entidades.Actividades Actividad = new App_Code.Entidades.Actividades
            {
                Id = ID
            };
            Actividad.ConsultarID();
            ddl_Prioridad.SelectedValue = Actividad.IdPromotor.ToString();
            tb_Titulo.Text = Actividad.Titulo;
            tb_Descripcion.Text = Actividad.Descripcion;
            ddl_Prioridad.SelectedValue = Actividad.IdPrioridad.ToString();

            ddl_Promotor.Enabled = false;
            ddl_Promotor.CssClass += " disabled";
            tb_Titulo.Enabled = false;
            tb_Titulo.CssClass += " disabled";
            tb_Descripcion.Enabled = false;
            tb_Descripcion.CssClass += " disabled";
            ddl_Prioridad.Enabled = false;
            ddl_Prioridad.CssClass += " disabled";
            b_Crear.Visible = false;
            b_Finalizar.Visible = false;
        }

        /// <summary>
        /// EVENTO PARA CREAR LA ACTIVIDAD
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void b_Crear_Click(object sender, EventArgs e)
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            App_Code.Entidades.Actividades Actividad = new App_Code.Entidades.Actividades
            {
                IdPromotor = int.Parse(ddl_Promotor.SelectedValue),
                Titulo = tb_Titulo.Text,
                Descripcion = tb_Descripcion.Text,
                IdPrioridad = int.Parse(ddl_Prioridad.SelectedValue),
                IdUsuario = IDUsuarioActual
            };
            Actividad.Agregar();
            Session["Alerta"] = Herramientas.Alerta("Operación existosa!", "Actividad creada correctamente.", 3);
            Response.Redirect("Listado.aspx");
        }

        /// <summary>
        /// EVENTO PARA FINALIZAR LA ACTIVDAD
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void b_Finalizar_Click(object sender, EventArgs e)
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            int ID = int.Parse(Request.QueryString["id"].ToString());
            App_Code.Entidades.Actividades Actividad = new App_Code.Entidades.Actividades
            {
                Id = ID,
                IdUsuario = IDUsuarioActual
            };
            Actividad.ConsultarID();
            Actividad.IdEstatus = 8;
            Actividad.Actualizar();
        }
    }
}