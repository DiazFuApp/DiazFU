using DiazFu.App_Code.Entidades;
using DiazFu.App_Code.Utilerias;
using System;
using System.Web.UI.WebControls;

namespace DiazFu.Modules.Actividades
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
        /// MÉTODO PARA CARGAR EL GRID
        /// </summary>
        public void CargarGrid()
        {
            int IDActor = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).IdActor.ToString(), out IDActor);
            int IDTipoActor = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).IdTipoActor.ToString(), out IDTipoActor);

            App_Code.Entidades.Actividades Actividades = new App_Code.Entidades.Actividades();
            if (IDTipoActor == 2)
            {
                Actividades.IdPromotor = IDActor;
            }
            gvActividades.DataSource = Actividades.ConsultarTodo();
            gvActividades.DataBind();
        }

        /// <summary>
        /// EVENTO PARA FINALIZAR LA ACTIVIDAD
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void bFinalizar_Click(object sender, EventArgs e)
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            Button boton = (Button)sender;
            int id = int.Parse(boton.CommandArgument);
            App_Code.Entidades.Actividades Actividad = new App_Code.Entidades.Actividades
            {
                Id = id,
                IdUsuario = IDUsuarioActual
            };
            Actividad.ConsultarID();
            Actividad.IdEstatus = 8;
            Actividad.Actualizar();

            Literal literal = (Literal)Master.FindControl("lAlerta");
            literal.Text = Herramientas.Alerta("Operación existosa!", "Actividad finalizada correctamente.", 3);
            CargarGrid();
        }

        /// <summary>
        /// EVENTO PARA MOSTRAR EL BOTÓN FINALIZAR
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void gvActividades_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                int IdEstatus = int.Parse(((System.Data.DataRowView)e.Row.DataItem).DataView[e.Row.DataItemIndex]["IdEstatus"].ToString());
                if (IdEstatus == 8)
                {
                    Button bFinalizar = (Button)e.Row.FindControl("bFinalizar");
                    bFinalizar.Visible = false;
                }
            }
        }
    }
}