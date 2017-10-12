using DiazFu.App_Code.Entidades;
using DiazFu.App_Code.Utilerias;
using System;
using System.Web.UI.WebControls;

namespace DiazFu.Modules.Administracion.Grupos
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
            App_Code.Entidades.Grupos Grupos = new App_Code.Entidades.Grupos();
            gvGrupos.DataSource = Grupos.ConsultarTodo();
            gvGrupos.DataBind();
        }

        /// <summary>
        /// EVENTO PARA MOSTRAR EL BOTÓN DE VALIDACIÓN
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void gvGrupos_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                int IdEstatus = int.Parse(((System.Data.DataRowView)e.Row.DataItem).DataView[e.Row.DataItemIndex]["IdEstatus"].ToString());
                int IdTipoActor = 0;
                int.TryParse(((Usuarios)Session["Usuario"]).IdTipoActor.ToString(), out IdTipoActor);
                if (IdEstatus == 3 && IdTipoActor == 1)
                {
                    Button bAutorizar = (Button)e.Row.FindControl("bAutorizar");
                    bAutorizar.Visible = true;
                }
            }
        }

        /// <summary>
        /// EVENTO PARA AUTORIZAR EL GRUPO
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void bAutorizar_Click(object sender, EventArgs e)
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            Button boton = (Button)sender;
            int id = int.Parse(boton.CommandArgument);
            App_Code.Entidades.Grupos Grupo = new App_Code.Entidades.Grupos
            {
                Id = id,
                IdUsuario = IDUsuarioActual
            };
            Grupo.ConsultarID();
            Grupo.IdEstatus = 4;
            Grupo.Actualizar();
            Literal literal = (Literal)Master.FindControl("lAlerta");
            if (Grupo.Id != 0)
            {
                literal.Text = Herramientas.Alerta("Operación existosa!", "Grupo autorizado correctamente.", 3);
            }
            else
            {
                literal.Text = Herramientas.Alerta("Ocurrió un error!", "No ha sido posible autorizar al grupo.", 4);
            }
            CargarGrid();
        }

        /// <summary>
        /// EVENTO PARA ELIMINAR EL GRUPO
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void bEliminar_Click(object sender, EventArgs e)
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            Button boton = (Button)sender;
            int id = int.Parse(boton.CommandArgument);
            App_Code.Entidades.Grupos Grupo = new App_Code.Entidades.Grupos
            {
                Id = id,
                IdUsuario = IDUsuarioActual
            };
            Grupo.ConsultarID();
            Grupo.IdEstatus = 2;
            Grupo.Actualizar();
            Literal literal = (Literal)Master.FindControl("lAlerta");
            if (Grupo.Id != 0)
            {
                literal.Text = Herramientas.Alerta("Operación existosa!", "Grupo eliminado correctamente.", 3);
            }
            else
            {
                literal.Text = Herramientas.Alerta("Ocurrió un error!", "No ha sido posible eliminar al grupo.", 4);
            }
            CargarGrid();
        }
    }
}