using DiazFu.App_Code.Utilerias;
using System;
using System.Data;
using System.Web.UI.WebControls;

namespace DiazFu.Modules.Prestamos.PrestamosGrupales
{
    public partial class Pagos : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Request.QueryString["id"] != null)
                {
                    ConsultarPrestamo();
                }
                else
                {
                    Response.Redirect("Listado.aspx");
                }
            }
        }

        /// <summary>
        /// MÉTODO PARA LA CARGA DE INTEGRANTES
        /// </summary>
        private void ConsultarPrestamo()
        {
            int IDGrupo = int.Parse(Request.QueryString["id"].ToString());
            App_Code.Entidades.IntegrantesGrupos Integrantes = new App_Code.Entidades.IntegrantesGrupos
            {
                IdGrupo = IDGrupo
            };
            r_Clientes.DataSource = Integrantes.ConsultarTodo();
            r_Clientes.DataBind();
        }

        /// <summary>
        /// EVENTO PARA REALIZAR EL PAGO DEL GRUPO
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void bPagar_Click(object sender, EventArgs e)
        {
            if (ValidarInformacion())
            {
                foreach (RepeaterItem Item in r_Clientes.Items)
                {
                    int IDPrestamoGrupal = int.Parse(Request.QueryString["id"].ToString());
                    int IDCliente = int.Parse(((HiddenField)Item.FindControl("hf_IdCliente")).Value);
                    double Monto = double.Parse(((TextBox)Item.FindControl("tb_Monto")).Text.Replace("$", ""));
                    if (Monto > 0.00)
                    {
                        App_Code.Entidades.Pagos PagosCliente = new App_Code.Entidades.Pagos
                        {
                            IdPrestamo = IDPrestamoGrupal,
                            IdTipoPrestamo = 2,
                            IdCliente = IDCliente
                        };
                        using (DataSet dsPagos = PagosCliente.ConsultarProximosPagos())
                        {
                            //foreach (DataRow Fila in dsPagos.Tables[0].Rows)
                            //{
                            //    while (Monto > 0.00)
                            //    {
                            //        App_Code.Entidades.Pagos Pago = new App_Code.Entidades.Pagos
                            //        {
                            //            Id = int.Parse(Fila["id"].ToString())
                            //        };
                            //        Pago.ConsultarID();
                            //        Pago.
                            //    }
                            //}
                        }

                    }
                }
            }
        }

        /// <summary>
        /// FUNCIÓN PARA VALIDAR LA INFORMACIÓN DEL FORMULARIO
        /// </summary>
        /// <returns></returns>
        private bool ValidarInformacion()
        {
            try
            {
                foreach (RepeaterItem Item in r_Clientes.Items)
                {
                    int IDCliente = int.Parse(((HiddenField)Item.FindControl("hf_IdCliente")).Value);
                    double Monto = double.Parse(((TextBox)Item.FindControl("tb_Monto")).Text.Replace("$", ""));
                    if (Monto > 0.00)
                    {
                        return true;
                    }
                }
                throw new Exception("Por favor, asigne el monto a pagar al menos a un cliente.");
            }
            catch (Exception ex)
            {
                Literal literal = (Literal)Master.FindControl("lAlerta");
                literal.Text = Herramientas.Alerta("Oops, ocurrió un error!", ex.Message, 4);
                return false;
            }
        }
    }
}