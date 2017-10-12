using System;

namespace DiazFu.Modules.Prestamos.Pagos
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
            App_Code.Entidades.Pagos Pagos = new App_Code.Entidades.Pagos();
            gvPrestamos.DataSource = Pagos.ConsultarProximosPagos();
            gvPrestamos.DataBind();
        }
    }
}