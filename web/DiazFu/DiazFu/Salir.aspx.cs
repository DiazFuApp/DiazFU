using System;

namespace DiazFu
{
    public partial class Salir : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Session["Usuario"] = null;
            Response.Redirect("~/Default.aspx");
        }
    }
}