using DiazFu.App_Code.Entidades;
using DiazFu.App_Code.Utilerias;
using System;
using System.Data;
using System.Web.UI.WebControls;

namespace DiazFu.Modules.Prestamos.PrestamosIndividuales
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
            int IDPrestamo = int.Parse(Request.QueryString["id"].ToString());
            App_Code.Entidades.PrestamosIndividuales Prestamo = new App_Code.Entidades.PrestamosIndividuales
            {
                Id = IDPrestamo
            };
            Prestamo.ConsultarID();
            App_Code.Entidades.Clientes Cliente = new App_Code.Entidades.Clientes()
            {
                Id = Prestamo.IdCliente
            };
            Cliente.ConsultarID();
            l_Cliente.Text = Cliente.Nombre;

            //CARGA DE CONTROLES
            App_Code.Entidades.Pagos PagosCliente = new App_Code.Entidades.Pagos()
            {
                IdPrestamo = IDPrestamo,
                IdTipoPrestamo = 1,
                IdCliente = Cliente.Id
            };

            using (DataSet dsConsulta = PagosCliente.ConsultarProximosPagos())
            {
                if (dsConsulta.Tables[0].Rows.Count > 0)
                {
                    double deuda = 0.00;
                    l_Plazo.Text = dsConsulta.Tables[0].Rows[0]["Plazo"].ToString();
                    foreach (DataRow Fila in dsConsulta.Tables[0].Rows)
                    {
                        deuda += double.Parse(Fila["MontoAPagar"].ToString()) - double.Parse(Fila["MontoPagado"].ToString());
                    }
                    l_Restante.Text = "$" + deuda.ToString();

                    //CONSULTA DE TODOS LOS PAGOS
                    using (DataSet dsConsultaPagos = PagosCliente.ConsultarTodo())
                    {
                        //ASIGNAR EL DATASOURCE AL LISTADO DEL CLIENTE
                        gv_PagosCliente.DataSource = dsConsultaPagos;
                        gv_PagosCliente.DataBind();
                    }
                }
                else
                {
                    tb_Monto.Enabled = false;
                    l_Restante.Text = "Liquidado";
                }
            }
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
                int IdUsuarioActual = 0;
                int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IdUsuarioActual);
                int IDPrestamo = int.Parse(Request.QueryString["id"].ToString());
                App_Code.Entidades.PrestamosIndividuales Prestamo = new App_Code.Entidades.PrestamosIndividuales
                {
                    Id = IDPrestamo,
                    IdUsuario = IdUsuarioActual
                };
                Prestamo.ConsultarID();
                int? IDCliente = Prestamo.IdCliente;
                float Monto = float.Parse(tb_Monto.Text.Replace("$", ""));
                if (Monto > 0.00)
                {
                    App_Code.Entidades.Pagos PagosCliente = new App_Code.Entidades.Pagos
                    {
                        IdPrestamo = IDPrestamo,
                        IdTipoPrestamo = 1,
                        IdCliente = IDCliente,
                        IdUsuario = IdUsuarioActual
                    };
                    using (DataSet dsPagos = PagosCliente.ConsultarProximosPagos())
                    {
                        foreach (DataRow fila in dsPagos.Tables[0].Rows)
                        {
                            if (Monto > 0.00)
                            {
                                App_Code.Entidades.Pagos Pago = new App_Code.Entidades.Pagos
                                {
                                    Id = int.Parse(fila["id"].ToString()),
                                    IdUsuario = IdUsuarioActual
                                };
                                Pago.ConsultarID();
                                //ASIGNAR TIPO DE PAGO
                                Pago.TipoPago = ddl_TipoPago.SelectedValue;
                                Monto += Pago.MontoPagado;

                                //CALCULO DEL MONTO
                                if (Pago.MontoAPagar <= Monto)
                                {
                                    Pago.MontoPagado = Pago.MontoAPagar;
                                    Pago.IdEstatus = 6;
                                    Monto -= Pago.MontoAPagar;
                                }
                                else
                                {
                                    Pago.MontoPagado = Monto;
                                    Monto -= Monto;
                                }
                                Pago.Actualizar();
                            }
                        }
                    }
                }

                //BUSQUEDA DE PAGOS PENDIENTES DEL PRESTAMO
                App_Code.Entidades.Pagos Pagos = new App_Code.Entidades.Pagos()
                {
                    IdPrestamo = IDPrestamo,
                    IdTipoPrestamo = 1,
                    IdCliente = IDCliente
                };
                using (DataSet PagosPendientes = Pagos.ConsultarProximosPagos())
                {
                    //CAMBIO DE ESTATUS DEL PRESTAMO A PAGADO
                    if (PagosPendientes.Tables[0].Rows.Count == 0)
                    {
                        Prestamo = new App_Code.Entidades.PrestamosIndividuales
                        {
                            Id = IDPrestamo,
                            IdUsuario = IdUsuarioActual
                        };
                        Prestamo.ConsultarID();
                        Prestamo.IdEstatus = 6;
                        Prestamo.Actualizar();
                    }
                }

                Session["Alerta"] = Herramientas.Alerta("Operación existosa!", "Pagos realizados correctamente.", 3);
                Response.Redirect("Listado.aspx");
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
                double Monto = double.Parse(tb_Monto.Text.Replace("$", ""));
                if (Monto > 0.00)
                {
                    return true;
                }
                throw new Exception("Por favor, asigne el monto a pagar para este cliente.");
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