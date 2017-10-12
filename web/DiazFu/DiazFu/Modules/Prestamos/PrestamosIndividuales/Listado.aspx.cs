using DiazFu.App_Code.Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
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

        /// <summary>
        /// EVENTO PARA MOSTRAR EL BOTÓN DE VALIDACIÓN
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void gvPrestamos_RowDataBound(object sender, System.Web.UI.WebControls.GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                //VALIDAR COLUMNA AUTORIZAR
                int IdTipoActor = 0;
                int.TryParse(((Usuarios)Session["Usuario"]).IdTipoActor.ToString(), out IdTipoActor);
                int IdEstatus = int.Parse(((System.Data.DataRowView)e.Row.DataItem).DataView[e.Row.DataItemIndex]["IdEstatus"].ToString());
                if (IdEstatus == 3 && IdTipoActor != 1 || IdEstatus == 5)
                {
                    HtmlAnchor aAutorizar = (HtmlAnchor)e.Row.FindControl("aAutorizar");
                    aAutorizar.InnerHtml = "Ver";
                }
                else if (IdEstatus == 4)
                {
                    HtmlAnchor aAutorizar = (HtmlAnchor)e.Row.FindControl("aAutorizar");
                    aAutorizar.InnerHtml = "Entregar";
                }
                else if (IdEstatus == 6)
                {
                    HtmlAnchor aAutorizar = (HtmlAnchor)e.Row.FindControl("aAutorizar");
                    aAutorizar.Visible = false;
                }
            }
        }
    }
}