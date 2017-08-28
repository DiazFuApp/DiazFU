using DiazFu.App_Code.Utilerias;
using System;
using System.Web.UI.WebControls;

namespace DiazFu.Modules.Administracion.Promotores
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
            App_Code.Entidades.Promotores Promotores = new App_Code.Entidades.Promotores();
            gvPromotores.DataSource = Promotores.ConsultarTodo();
            gvPromotores.DataBind();
        }

        /// <summary>
        /// Evento para eliminar el promotor
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void bEliminar_Click(object sender, EventArgs e)
        {
            Button boton = (Button)sender;
            int id = int.Parse(boton.CommandArgument);
            App_Code.Entidades.Promotores Promotor = new App_Code.Entidades.Promotores();
            Promotor.Id = id;
            Promotor.ConsultarID();
            //Promotor.IdEstatus = 0;
            Promotor.Actualizar();
            Literal literal = (Literal)Master.FindControl("lAlerta");
            if (Promotor.Id != 0)
            {
                literal.Text = Herramientas.Alerta("Operación existosa!", "Promotor eliminado correctamente.", 3);
                //updated
            }
            else
            {
                literal.Text = Herramientas.Alerta("Ocurrió un error!", "No ha sido posible eliminar al promotor.", 4);
                //not updated
            }
            CargarGrid();
        }
    }
}