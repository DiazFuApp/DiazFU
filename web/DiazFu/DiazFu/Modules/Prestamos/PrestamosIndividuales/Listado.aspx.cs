using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DiazFu.Modules.Prestamos.PrestamosIndividuales
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
            App_Code.Entidades.PrestamosIndividuales Prestamos = new App_Code.Entidades.PrestamosIndividuales();
            gvPrestamos.DataSource = Prestamos.ConsultarTodo();
            gvPrestamos.DataBind();
        }
    }
}