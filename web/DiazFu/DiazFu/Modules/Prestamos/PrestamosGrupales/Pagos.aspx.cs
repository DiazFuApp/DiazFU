using DiazFu.App_Code.Entidades;
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
            int IDPrestamo = int.Parse(Request.QueryString["id"].ToString());
            App_Code.Entidades.PrestamosGrupales Prestamo = new App_Code.Entidades.PrestamosGrupales
            {
                Id = IDPrestamo
            };
            Prestamo.ConsultarID();
            App_Code.Entidades.IntegrantesGrupos Integrantes = new App_Code.Entidades.IntegrantesGrupos
            {
                IdGrupo = Prestamo.IdGrupo
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
                int IdUsuarioActual = 0;
                int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IdUsuarioActual);
                int IDPrestamoGrupal = int.Parse(Request.QueryString["id"].ToString());
                foreach (RepeaterItem Item in r_Clientes.Items)
                {
                    int IDCliente = int.Parse(((HiddenField)Item.FindControl("hf_IdCliente")).Value);
                    float Monto = float.Parse(((TextBox)Item.FindControl("tb_Monto")).Text.Replace("$", ""));
                    float Morosidad = float.Parse("0.00");
                    if (Monto > 0.00)
                    {
                        App_Code.Entidades.Pagos PagosCliente = new App_Code.Entidades.Pagos
                        {
                            IdPrestamo = IDPrestamoGrupal,
                            IdTipoPrestamo = 2,
                            IdCliente = IDCliente,
                            IdUsuario = IdUsuarioActual
                        };
                        using (DataSet dsPagos = PagosCliente.ConsultarProximosPagos())
                        {
                            foreach (DataRow fila in dsPagos.Tables[0].Rows)
                            {
                                if (Monto > 0.00)
                                {
                                    //VALIDACIÓN DEL ÚLTIMO REGISTRO
                                    //ESTO PARA AGREGAR LA MOROSIDAD AL PAGO PARA LIQUIDAR EL ADEUDO
                                    if (fila == dsPagos.Tables[0].Rows[dsPagos.Tables[0].Rows.Count - 1])
                                    {
                                        Morosidad = float.Parse(((TextBox)Item.FindControl("tb_Morosidad")).Text.Replace("$", ""));
                                    }

                                    App_Code.Entidades.Pagos Pago = new App_Code.Entidades.Pagos
                                    {
                                        Id = int.Parse(fila["id"].ToString())
                                    };
                                    Pago.ConsultarID();
                                    //ASIGNAR TIPO DE PAGO
                                    Pago.TipoPago = ddl_TipoPago.SelectedValue;
                                    Monto += Pago.MontoPagado;

                                    //CALCULO DEL MONTO
                                    if (Pago.MontoAPagar <= Monto)
                                    {
                                        Pago.MontoPagado = Pago.MontoAPagar;
                                        if (Morosidad > 0.00)
                                        {
                                            Pago.MontoPagado += Morosidad;
                                        }
                                        Pago.IdEstatus = 6;
                                        Monto -= Pago.MontoAPagar;
                                    }
                                    else
                                    {
                                        Pago.MontoPagado = Monto;
                                        Monto -= Monto;
                                    }

                                    //CALCULO DE MOROSIDAD
                                    Pago.FechaPago = DateTime.Now;
                                    if (Pago.FechaProgramada < DateTime.Now)
                                    {
                                        double totalDias = Math.Ceiling((DateTime.Now - Pago.FechaProgramada).TotalDays);
                                        Pago.Morosidad = float.Parse(totalDias.ToString()) * float.Parse("50.00");
                                    }
                                    Pago.Actualizar();
                                }
                            }
                        }
                    }
                }

                //BUSQUEDA DE PAGOS PENDIENTES DEL PRESTAMO
                App_Code.Entidades.Pagos Pagos = new App_Code.Entidades.Pagos()
                {
                    IdPrestamo = IDPrestamoGrupal,
                    IdTipoPrestamo = 2
                };
                using (DataSet PagosPendientes = Pagos.ConsultarProximosPagos())
                {
                    //CAMBIO DE ESTATUS DEL PRESTAMO A PAGADO
                    if (PagosPendientes.Tables[0].Rows.Count == 0)
                    {
                        App_Code.Entidades.PrestamosGrupales Prestamo = new App_Code.Entidades.PrestamosGrupales
                        {
                            Id = IDPrestamoGrupal
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

        /// <summary>
        /// EVENTO PARA CARGAR LOS DATOS DEL REPEATER
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void r_Clientes_ItemDataBound(object sender, RepeaterItemEventArgs e)
        {
            if (e.Item.DataItem != null)
            {
                App_Code.Entidades.Pagos PagosClientes = new App_Code.Entidades.Pagos()
                {
                    IdPrestamo = int.Parse(Request.QueryString["id"].ToString()),
                    IdCliente = int.Parse(((DataRowView)e.Item.DataItem).Row.ItemArray[0].ToString()),
                    IdTipoPrestamo = 2
                };

                //PAGOS
                using (DataSet dsConsulta = PagosClientes.ConsultarProximosPagos())
                {
                    if (dsConsulta.Tables[0].Rows.Count > 0)
                    {
                        double deuda = 0.00;
                        Label l_Plazo = (Label)e.Item.FindControl("l_Plazo");
                        l_Plazo.Text = dsConsulta.Tables[0].Rows[0]["Plazo"].ToString();
                        foreach (DataRow Fila in dsConsulta.Tables[0].Rows)
                        {
                            deuda += double.Parse(Fila["MontoAPagar"].ToString()) - double.Parse(Fila["MontoPagado"].ToString());
                        }
                        Label l_Restante = (Label)e.Item.FindControl("l_Restante");
                        l_Restante.Text = "$" + deuda.ToString();

                        //MOSTRAR MOROSIDAD
                        using (DataSet dsConsultaMorosidad = PagosClientes.ConsultarTodo())
                        {
                            double morosidad = 0.00;
                            foreach (DataRow Fila in dsConsultaMorosidad.Tables[0].Rows)
                            {
                                if (int.Parse(Fila["IdEstatus"].ToString()) == 7)
                                {
                                    DateTime FechaPago = DateTime.Parse(Fila["FechaProgramada"].ToString());
                                    if (FechaPago < DateTime.Now)
                                    {
                                        double totalDias = Math.Ceiling((DateTime.Now - FechaPago).TotalDays);
                                        morosidad = float.Parse(totalDias.ToString()) * float.Parse("50.00");
                                        Fila["Morosidad"] = morosidad;
                                    }
                                }
                                else
                                {
                                    morosidad += double.Parse(Fila["Morosidad"].ToString());
                                }
                            }
                            //ASIGNAR EL DATASOURCE AL LISTADO DEL CLIENTE
                            GridView gv_PagosClientes = (GridView)e.Item.FindControl("gv_PagosCliente");
                            gv_PagosClientes.DataSource = dsConsultaMorosidad;
                            gv_PagosClientes.DataBind();

                            TextBox tb_Morosidad = (TextBox)e.Item.FindControl("tb_Morosidad");
                            tb_Morosidad.Text = "$" + morosidad.ToString();
                        }
                    }
                    else
                    {
                        TextBox tb_Monto = (TextBox)e.Item.FindControl("tb_Monto");
                        tb_Monto.Enabled = false;
                        Label l_Restante = (Label)e.Item.FindControl("l_Restante");
                        l_Restante.Text = "Liquidado";
                        TextBox tb_Morosidad = (TextBox)e.Item.FindControl("tb_Morosidad");
                        tb_Morosidad.Enabled = false;
                        tb_Morosidad.Text = "Liquidado";
                    }
                }
            }
        }
    }
}