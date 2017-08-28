using System;
using DiazFu.App_Code.Entidades;
using DiazFu.App_Code.Utilerias;

namespace DiazFu
{
    public partial class Default : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void b_iniciar_sesion_Click(object sender, EventArgs e)
        {
            Usuarios Usuario = new Usuarios
            {
                Nombre = tb_usuario.Text,
                Contrasena = tb_contrasena.Text
            };
            Usuario.LogIn();
            if (Usuario.Id != null)
            {
                Response.Redirect("Modules/Administracion/Promotores/Listado.aspx");
            }
            else
            {
                lAlerta.Text = Herramientas.Alerta("Ocurrió un error!", "Usuario y/o contraseña incorrecta.", 4);
            }
        }
    }
}