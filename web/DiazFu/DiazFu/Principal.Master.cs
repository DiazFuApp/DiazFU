using DiazFu.App_Code.Entidades;
using System;

namespace DiazFu
{
    public partial class Principal : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Alerta"] != null)
            {
                lAlerta.Text = Session["Alerta"].ToString();
                Session["Alerta"] = null;
            }
            if (Session["Usuario"] == null)
            {
                Response.Redirect("~/Default.aspx");
            }
            else
            {
                int IdTipoActor = 0;
                int.TryParse(((Usuarios)Session["Usuario"]).IdTipoActor.ToString(), out IdTipoActor);
                switch (IdTipoActor)
                {
                    case 2:
                        lbPromotores.Visible = false;
                        break;
                    default:
                        break;
                }
            }
        }
    }
}