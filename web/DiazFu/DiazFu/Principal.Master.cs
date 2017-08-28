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
        }
    }
}