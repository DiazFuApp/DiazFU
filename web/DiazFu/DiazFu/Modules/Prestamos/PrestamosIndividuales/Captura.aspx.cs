using DiazFu.App_Code.Entidades;
using DiazFu.App_Code.Utilerias;
using System;
using System.Data;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DiazFu.Modules.Prestamos.PrestamosIndividuales
{
    public partial class Captura : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargaControles();
                if (Request.QueryString["id"] != null)
                {
                    ConsultarPrestamo();
                }
            }
        }

        /// <summary>
        /// CARGA DE LOS CONTROLES
        /// </summary>
        private void CargaControles()
        {
            //CARGA DE LOS GRUPOS
            Clientes Clientes = new Clientes();
            ddl_Cliente.DataSource = Clientes.ConsultarTodo();
            ddl_Cliente.DataTextField = "Nombre";
            ddl_Cliente.DataValueField = "Id";
            ddl_Cliente.DataBind();

            //CARGA DEL REPEATER
            Clientes repClientes = new Clientes();
            if (ddl_Cliente.Items.Count > 0)
            {
                repClientes.Id = int.Parse(ddl_Cliente.SelectedValue);
                r_Documentos.DataSource = repClientes.ConsultarTodo();
                r_Documentos.DataBind();
            }
        }

        /// <summary>
        /// EVENTO PARA OBTENER LOS ITEMS DEL REPEATER
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void r_Documentos_ItemDataBound(object sender, RepeaterItemEventArgs e)
        {
            if (e.Item.DataItem != null)
            {
                Documentos Documentos = new Documentos()
                {
                    IdActor = int.Parse(((DataRowView)e.Item.DataItem).Row.ItemArray[0].ToString()),
                    IdTipoActor = 3
                };
                GridView Grid = (GridView)e.Item.FindControl("gvDocumentos");
                Grid.DataSource = Documentos.ConsultarTodo();
                Grid.DataBind();
            }
        }

        //EVENTO PARA INGRESAR LA FECHA DE PAGO PROGRAMADA
        protected void gvPagos_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                string Plazos = ((System.Data.DataRowView)e.Row.DataItem).DataView[e.Row.DataItemIndex]["Plazo"].ToString();
                int Plazo = int.Parse(Plazos.Substring(0, Plazos.IndexOf('/')));
                if (((System.Data.DataRowView)e.Row.DataItem).DataView[e.Row.DataItemIndex]["FechaProgramada"].ToString() == string.Empty)
                {
                    TableCell FechaProgramada = e.Row.Cells[3];
                    FechaProgramada.Text = DateTime.Now.AddDays(Plazo * 30).ToShortDateString();
                }
            }
        }

        /// <summary>
        /// MÉTODO PARA CARGAR LOS DATOS DEL PRÉSTAMO
        /// </summary>
        private void ConsultarPrestamo()
        {
            //-------------------------------------------------------------------------------------------------
            //CONSULTA DE DATOS
            #region Prestamo
            App_Code.Entidades.PrestamosIndividuales Prestamo = new App_Code.Entidades.PrestamosIndividuales
            {
                Id = int.Parse(Request.QueryString["Id"].ToString())
            };
            Prestamo.ConsultarID();
            ddl_Cliente.SelectedValue = Prestamo.IdCliente.ToString();
            tb_Motivo.Text = Prestamo.Motivo;
            tb_CantidadSolicitada.Text = Prestamo.CantidadSolicitada.ToString();
            tb_Garantia.Text = Prestamo.Garantia;
            tb_Observaciones.Text = Prestamo.Observaciones;
            CargaControles();

            int IdTipoActor = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).IdTipoActor.ToString(), out IdTipoActor);
            if (IdTipoActor == 1)
            {
                //HABILITAR PANEL DE AUTORIZACION
                jtAutorizacion.Visible = true;
            }
            #endregion

            //CONSULTA DE AVAL
            //-------------------------------------------------------------------------------------------------
            //AVAL
            #region Aval
            ReferenciasPrestamos Aval = new ReferenciasPrestamos
            {
                IdPrestamo = Prestamo.Id,
                IdTipoReferencia = 2,
                IdTipoPrestamo = 1
            };
            Aval.ConsultarID();
            tb_aval_Nombre.Text = Aval.Nombre;
            tb_aval_RFC.Text = Aval.RFC;
            tb_aval_Direccion.Text = Aval.Direccion;
            tb_aval_DireccionReferencia.Text = Aval.ReferenciaDireccion;
            tb_aval_TelefonoCasa.Text = Aval.TelefonoCasa;
            tb_aval_TelefonoCelular.Text = Aval.TelefonoCelular;
            tb_aval_CorreoElectronico.Text = Aval.CorreoElectronico;
            tb_aval_FechaNacimiento.Text = Aval.FechaNacimiento.ToString();
            tb_aval_CURP.Text = Aval.CURP;
            tb_aval_Parentesco.Text = Aval.Parentesco;
            tb_aval_ClaveElector.Text = Aval.ClaveElector;
            tb_aval_NombreEmpresa.Text = Aval.Empresa;
            tb_aval_PuestoEmpresa.Text = Aval.PuestoEmpresa;
            tb_aval_AntiguedadEmpresa.Text = Aval.AntiguedadEmpresa;
            tb_aval_TelefonoEmpresa.Text = Aval.TelefonoEmpresa;
            tb_aval_NombreJefeEmpresa.Text = Aval.NombreJefe;
            tb_aval_DireccionEmpresa.Text = Aval.DireccionEmpresa;
            div_aval_Foto.Visible = true;
            a_aval_Foto.HRef = Aval.URLFoto;
            fu_aval_Foto.Attributes.Remove("required");
            //-------------------------------------------------------------------------------------------------
            //REDES AVAL
            RedesSociales AvalFacebook = new RedesSociales
            {
                IdActor = Aval.Id,
                IdTipoActor = 7,
                IdTipoRedSocial = 1
            };
            AvalFacebook.ConsultarID();
            tb_aval_Facebook.Text = AvalFacebook.URL;
            RedesSociales AvalTwitter = new RedesSociales
            {
                IdActor = Aval.Id,
                IdTipoActor = 7,
                IdTipoRedSocial = 2
            };
            AvalTwitter.ConsultarID();
            tb_aval_Twitter.Text = AvalTwitter.URL;
            RedesSociales AvalInstagram = new RedesSociales
            {
                IdActor = Aval.Id,
                IdTipoActor = 7,
                IdTipoRedSocial = 3
            };
            AvalInstagram.ConsultarID();
            tb_aval_Instagram.Text = AvalInstagram.URL;
            //-------------------------------------------------------------------------------------------------
            //DOCUMENTOS
            Documentos AvalActaNacimiento = new Documentos
            {
                IdTipoActor = 7,
                IdActor = Aval.Id,
                IdTipoDocumento = 1
            };
            AvalActaNacimiento.ConsultarID();
            a_aval_ActaNacimiento.HRef = AvalActaNacimiento.URLDocumento;
            div_aval_ActaNacimiento.Visible = true;
            fu_aval_ActaNacimiento.Attributes.Remove("required");
            Documentos AvalConstanciaResidencia = new Documentos
            {
                IdTipoActor = 7,
                IdActor = Aval.Id,
                IdTipoDocumento = 4
            };
            AvalConstanciaResidencia.ConsultarID();
            a_aval_ConstanciaResidencia.HRef = AvalConstanciaResidencia.URLDocumento;
            div_aval_ConstanciaResidencia.Visible = true;
            fu_aval_ConstanciaResidencia.Attributes.Remove("required");
            Documentos AvalCURP = new Documentos
            {
                IdTipoActor = 7,
                IdActor = Aval.Id,
                IdTipoDocumento = 3
            };
            AvalCURP.ConsultarID();
            a_aval_CURP.HRef = AvalCURP.URLDocumento;
            div_aval_CURP.Visible = true;
            fu_aval_CURP.Attributes.Remove("required");
            Documentos AvalINE = new Documentos
            {
                IdTipoActor = 7,
                IdActor = Aval.Id,
                IdTipoDocumento = 2
            };
            AvalINE.ConsultarID();
            a_aval_INE.HRef = AvalINE.URLDocumento;
            div_aval_INE.Visible = true;
            fu_aval_INE.Attributes.Remove("required");
            Documentos AvalComprobanteDomicilio = new Documentos
            {
                IdTipoActor = 7,
                IdActor = Aval.Id,
                IdTipoDocumento = 5
            };
            AvalComprobanteDomicilio.ConsultarID();
            a_aval_ComprobanteDomicilio.HRef = AvalComprobanteDomicilio.URLDocumento;
            div_aval_ComprobanteDomicilio.Visible = true;
            fu_aval_ComprobanteDomicilio.Attributes.Remove("required");
            #endregion
            //-------------------------------------------------------------------------------------------------

            //CONSULTA DE REFERENCIAS
            //-------------------------------------------------------------------------------------------------
            //CONSULTA
            ReferenciasPrestamos Referencias = new ReferenciasPrestamos
            {
                IdPrestamo = Prestamo.Id,
                IdTipoReferencia = 1,
                IdTipoPrestamo = 1
            };
            DataSet Consulta = Referencias.ConsultarTodo();
            //PRIMER REFERENCIA
            #region Primer Referencia
            ReferenciasPrestamos PrimerReferencia = new ReferenciasPrestamos
            {
                IdPrestamo = Prestamo.Id,
                IdTipoReferencia = 1,
                IdTipoPrestamo = 1
            };
            PrimerReferencia.ConsultarID();
            tb_pr_Nombre.Text = PrimerReferencia.Nombre;
            tb_pr_RFC.Text = PrimerReferencia.RFC;
            tb_pr_Direccion.Text = PrimerReferencia.Direccion;
            tb_pr_DireccionReferencia.Text = PrimerReferencia.ReferenciaDireccion;
            tb_pr_TelefonoCasa.Text = PrimerReferencia.TelefonoCasa;
            tb_pr_TelefonoCelular.Text = PrimerReferencia.TelefonoCelular;
            tb_pr_CorreoElectronico.Text = PrimerReferencia.CorreoElectronico;
            tb_pr_FechaNacimiento.Text = PrimerReferencia.FechaNacimiento.ToString();
            tb_pr_CURP.Text = PrimerReferencia.CURP;
            tb_pr_ClaveElector.Text = PrimerReferencia.ClaveElector;
            div_pr_Foto.Visible = true;
            a_pr_Foto.HRef = PrimerReferencia.URLFoto;
            fu_pr_Foto.Attributes.Remove("required");
            //-------------------------------------------------------------------------------------------------
            //REDES
            RedesSociales PrimerReferenciaFacebook = new RedesSociales
            {
                IdActor = PrimerReferencia.Id,
                IdTipoActor = 7,
                IdTipoRedSocial = 1
            };
            PrimerReferenciaFacebook.ConsultarRedesSocialesReferenciasPrestamos();
            tb_pr_Facebook.Text = PrimerReferenciaFacebook.URL;
            RedesSociales PrimerReferenciaTwitter = new RedesSociales
            {
                IdActor = PrimerReferencia.Id,
                IdTipoActor = 7,
                IdTipoRedSocial = 2
            };
            PrimerReferenciaTwitter.ConsultarRedesSocialesReferenciasPrestamos();
            tb_pr_Twitter.Text = PrimerReferenciaTwitter.URL;
            RedesSociales PrimerReferenciaInstagram = new RedesSociales
            {
                IdActor = PrimerReferencia.Id,
                IdTipoActor = 7,
                IdTipoRedSocial = 3
            };
            PrimerReferenciaInstagram.ConsultarRedesSocialesReferenciasPrestamos();
            tb_pr_Instagram.Text = PrimerReferenciaInstagram.URL;
            //-------------------------------------------------------------------------------------------------
            //DOCUMENTOS
            Documentos PrimerReferenciaActaNacimiento = new Documentos
            {
                IdTipoActor = 7,
                IdActor = PrimerReferencia.Id,
                IdTipoDocumento = 1
            };
            PrimerReferenciaActaNacimiento.ConsultarID();
            a_pr_ActaNacimiento.HRef = PrimerReferenciaActaNacimiento.URLDocumento;
            div_pr_ActaNacimiento.Visible = true;
            fu_pr_ActaNacimiento.Attributes.Remove("required");
            Documentos PrimerReferenciaConstanciaResidencia = new Documentos
            {
                IdTipoActor = 7,
                IdActor = PrimerReferencia.Id,
                IdTipoDocumento = 4
            };
            PrimerReferenciaConstanciaResidencia.ConsultarID();
            a_pr_ConstanciaResidencia.HRef = PrimerReferenciaConstanciaResidencia.URLDocumento;
            div_pr_ConstanciaResidencia.Visible = true;
            fu_pr_ConstanciaResidencia.Attributes.Remove("required");
            Documentos PrimerReferenciaCURP = new Documentos
            {
                IdTipoActor = 7,
                IdActor = PrimerReferencia.Id,
                IdTipoDocumento = 3
            };
            PrimerReferenciaCURP.ConsultarID();
            a_pr_CURP.HRef = PrimerReferenciaCURP.URLDocumento;
            div_pr_CURP.Visible = true;
            fu_pr_CURP.Attributes.Remove("required");
            Documentos PrimerReferenciaINE = new Documentos
            {
                IdTipoActor = 7,
                IdActor = PrimerReferencia.Id,
                IdTipoDocumento = 2
            };
            PrimerReferenciaINE.ConsultarID();
            a_pr_INE.HRef = PrimerReferenciaINE.URLDocumento;
            div_pr_INE.Visible = true;
            fu_pr_INE.Attributes.Remove("required");
            Documentos PrimerReferenciaComprobanteDomicilio = new Documentos
            {
                IdTipoActor = 7,
                IdActor = PrimerReferencia.Id,
                IdTipoDocumento = 5
            };
            PrimerReferenciaComprobanteDomicilio.ConsultarID();
            a_pr_ComprobanteDomicilio.HRef = PrimerReferenciaComprobanteDomicilio.URLDocumento;
            div_pr_ComprobanteDomicilio.Visible = true;
            fu_pr_ComprobanteDomicilio.Attributes.Remove("required");
            #endregion
            //REFERENCIA 2
            #region Segunda Referencia
            if (Consulta.Tables[0].Rows.Count > 1)
            {
                ReferenciasPrestamos SegundaReferencia = new ReferenciasPrestamos
                {
                    Id = int.Parse(Consulta.Tables[0].Rows[1]["Id"].ToString()),
                    IdTipoPrestamo = 1,
                    IdTipoReferencia = 1
                };
                SegundaReferencia.ConsultarID();
                tb_sr_Nombre.Text = SegundaReferencia.Nombre;
                tb_sr_RFC.Text = SegundaReferencia.RFC;
                tb_sr_Direccion.Text = SegundaReferencia.Direccion;
                tb_sr_DireccionReferencia.Text = SegundaReferencia.ReferenciaDireccion;
                tb_sr_TelefonoCasa.Text = SegundaReferencia.TelefonoCasa;
                tb_sr_TelefonoCelular.Text = SegundaReferencia.TelefonoCelular;
                tb_sr_CorreoElectronico.Text = SegundaReferencia.CorreoElectronico;
                tb_sr_FechaNacimiento.Text = SegundaReferencia.FechaNacimiento.ToString();
                tb_sr_CURP.Text = SegundaReferencia.CURP;
                tb_sr_ClaveElector.Text = SegundaReferencia.ClaveElector;
                div_sr_Foto.Visible = true;
                a_sr_Foto.HRef = SegundaReferencia.URLFoto;
                fu_sr_Foto.Attributes.Remove("required");
                //-------------------------------------------------------------------------------------------------
                //REDES
                RedesSociales SegundaReferenciaFacebook = new RedesSociales
                {
                    IdActor = SegundaReferencia.Id,
                    IdTipoActor = 5,
                    IdTipoRedSocial = 1
                };
                SegundaReferenciaFacebook.ConsultarRedesSocialesReferenciasPrestamos();
                tb_sr_Facebook.Text = SegundaReferenciaFacebook.URL;
                RedesSociales SegundaReferenciaTwitter = new RedesSociales
                {
                    IdActor = SegundaReferencia.Id,
                    IdTipoActor = 5,
                    IdTipoRedSocial = 2
                };
                SegundaReferenciaTwitter.ConsultarRedesSocialesReferenciasPrestamos();
                tb_sr_Twitter.Text = SegundaReferenciaTwitter.URL;
                RedesSociales SegundaReferenciaInstagram = new RedesSociales
                {
                    IdActor = SegundaReferencia.Id,
                    IdTipoActor = 5,
                    IdTipoRedSocial = 3
                };
                SegundaReferenciaInstagram.ConsultarRedesSocialesReferenciasPrestamos();
                tb_sr_Instagram.Text = SegundaReferenciaInstagram.URL;
                //-------------------------------------------------------------------------------------------------
                //DOCUMENTOS
                Documentos SegundaReferenciaActaNacimiento = new Documentos
                {
                    IdTipoActor = 5,
                    IdActor = SegundaReferencia.Id,
                    IdTipoDocumento = 1
                };
                SegundaReferenciaActaNacimiento.ConsultarID();
                a_sr_ActaNacimiento.HRef = SegundaReferenciaActaNacimiento.URLDocumento;
                div_sr_ActaNacimiento.Visible = true;
                fu_sr_ActaNacimiento.Attributes.Remove("required");
                Documentos SegundaReferenciaConstanciaResidencia = new Documentos
                {
                    IdTipoActor = 5,
                    IdActor = SegundaReferencia.Id,
                    IdTipoDocumento = 4
                };
                SegundaReferenciaConstanciaResidencia.ConsultarID();
                a_sr_ConstanciaResidencia.HRef = SegundaReferenciaConstanciaResidencia.URLDocumento;
                div_sr_ConstanciaResidencia.Visible = true;
                fu_sr_ConstanciaResidencia.Attributes.Remove("required");
                Documentos SegundaReferenciaCURP = new Documentos
                {
                    IdTipoActor = 5,
                    IdActor = SegundaReferencia.Id,
                    IdTipoDocumento = 3
                };
                SegundaReferenciaCURP.ConsultarID();
                a_sr_CURP.HRef = SegundaReferenciaCURP.URLDocumento;
                div_sr_CURP.Visible = true;
                fu_sr_CURP.Attributes.Remove("required");
                Documentos SegundaReferenciaINE = new Documentos
                {
                    IdTipoActor = 5,
                    IdActor = SegundaReferencia.Id,
                    IdTipoDocumento = 2
                };
                SegundaReferenciaINE.ConsultarID();
                a_sr_INE.HRef = SegundaReferenciaINE.URLDocumento;
                div_sr_INE.Visible = true;
                fu_sr_INE.Attributes.Remove("required");
                Documentos SegundaReferenciaComprobanteDomicilio = new Documentos
                {
                    IdTipoActor = 5,
                    IdActor = SegundaReferencia.Id,
                    IdTipoDocumento = 5
                };
                SegundaReferenciaComprobanteDomicilio.ConsultarID();
                a_sr_ComprobanteDomicilio.HRef = SegundaReferenciaComprobanteDomicilio.URLDocumento;
                div_sr_ComprobanteDomicilio.Visible = true;
                fu_sr_ComprobanteDomicilio.Attributes.Remove("required");
            }
            #endregion
            //-------------------------------------------------------------------------------------------------

            b_Crear.Visible = false;

            //-------------------------------------------------------------------------------------------------
            //BLOQUEO DE CONTROLES
            //DATOS DEL PRESTAMO
            foreach (Control c in jtDatosPrestamo.Controls)
            {
                if (c is DropDownList)
                {
                    DropDownList ddl = (DropDownList)c;
                    ddl.Enabled = false;
                    ddl.CssClass += " disabled";
                }
                else if (c is TextBox)
                {
                    TextBox tb = (TextBox)c;
                    tb.Enabled = false;
                    tb.CssClass += " disabled";
                }
            }
            //DOCUMENTOS
            foreach (Control c in jtDocumentos.Controls)
            {
                if (c is GridView)
                {
                    GridView gv = (GridView)c;
                    gv.Enabled = false;
                    gv.CssClass += " disabled";
                }
            }
            //AVAL
            foreach (Control c in jtAval.Controls)
            {
                if (c is FileUpload)
                {
                    FileUpload fu = (FileUpload)c;
                    fu.Enabled = false;
                    fu.CssClass += " disabled";
                }
                else if (c is TextBox)
                {
                    TextBox tb = (TextBox)c;
                    tb.Enabled = false;
                    tb.CssClass += " disabled";
                }
            }
            //PRIMER REFERENCIA
            foreach (Control c in jtPrimerReferencia.Controls)
            {
                if (c is FileUpload)
                {
                    FileUpload fu = (FileUpload)c;
                    fu.Enabled = false;
                    fu.CssClass += " disabled";
                }
                else if (c is TextBox)
                {
                    TextBox tb = (TextBox)c;
                    tb.Enabled = false;
                    tb.CssClass += " disabled";
                }
            }
            //SEGUNDA REFERENCIA
            foreach (Control c in jtSegundaReferencia.Controls)
            {
                if (c is FileUpload)
                {
                    FileUpload fu = (FileUpload)c;
                    fu.Enabled = false;
                    fu.CssClass += " disabled";
                }
                else if (c is TextBox)
                {
                    TextBox tb = (TextBox)c;
                    tb.Enabled = false;
                    tb.CssClass += " disabled";
                }
            }

            App_Code.Entidades.Pagos Pagos = new App_Code.Entidades.Pagos
            {
                IdPrestamo = Prestamo.Id,
                IdTipoPrestamo = 1
            };

            switch (Prestamo.IdEstatus)
            {
                case 4:
                    jtAutorizacion.Visible = true;
                    jEntrega.Visible = true;
                    tb_CantidadAOtorgar.Text = Prestamo.CantidadOtorgada.ToString();
                    tb_Interes.Text = Prestamo.Interes.ToString() + "%";
                    tb_Anticipo.Text = Prestamo.Anticipo.ToString();

                    //AUTORIZACIÓN
                    foreach (Control c in jtAutorizacion.Controls)
                    {
                        if (c is DropDownList)
                        {
                            DropDownList ddl = (DropDownList)c;
                            ddl.Enabled = false;
                            ddl.CssClass += " disabled";
                        }
                        else if (c is TextBox)
                        {
                            TextBox tb = (TextBox)c;
                            tb.Enabled = false;
                            tb.CssClass += " disabled";
                        }
                        else if (c is Button)
                        {
                            Button bt = (Button)c;
                            bt.Visible = false;
                        }
                    }

                    //CARGA GRID DE PAGOS
                    using (DataSet dsPagos = Pagos.ConsultarTodo())
                    {
                        gvPagos.DataSource = dsPagos;
                        gvPagos.DataBind();

                        string PlazosBD = dsPagos.Tables[0].Rows[0]["Plazo"].ToString();
                        string Plazos = PlazosBD.Substring(PlazosBD.IndexOf("/") + 1);
                        ddl_Plazos.SelectedValue = Plazos;
                    }
                    break;
                case 5:
                case 6:
                    jtAutorizacion.Visible = true;
                    jEntrega.Visible = true;
                    tb_CantidadAOtorgar.Text = Prestamo.CantidadOtorgada.ToString();
                    tb_Interes.Text = Prestamo.Interes.ToString() + "%";
                    tb_Anticipo.Text = Prestamo.Anticipo.ToString();

                    //AUTORIZACIÓN
                    foreach (Control c in jtAutorizacion.Controls)
                    {
                        if (c is DropDownList)
                        {
                            DropDownList ddl = (DropDownList)c;
                            ddl.Enabled = false;
                            ddl.CssClass += " disabled";
                        }
                        else if (c is TextBox)
                        {
                            TextBox tb = (TextBox)c;
                            tb.Enabled = false;
                            tb.CssClass += " disabled";
                        }
                        else if (c is Button)
                        {
                            Button bt = (Button)c;
                            bt.Visible = false;
                        }
                    }

                    //ENTREGA
                    foreach (Control c in jEntrega.Controls)
                    {
                        if (c is DropDownList)
                        {
                            DropDownList ddl = (DropDownList)c;
                            ddl.Enabled = false;
                            ddl.CssClass += " disabled";
                        }
                        else if (c is TextBox)
                        {
                            TextBox tb = (TextBox)c;
                            tb.Enabled = false;
                            tb.CssClass += " disabled";
                        }
                        else if (c is Button)
                        {
                            Button bt = (Button)c;
                            bt.Visible = false;
                        }
                    }

                    //CARGA GRID DE PAGOS
                    using (DataSet dsPagos = Pagos.ConsultarTodo())
                    {
                        gvPagos.DataSource = dsPagos;
                        gvPagos.DataBind();

                        string PlazosBD = dsPagos.Tables[0].Rows[0]["Plazo"].ToString();
                        string Plazos = PlazosBD.Substring(PlazosBD.IndexOf("/") + 1);
                        ddl_Plazos.SelectedValue = Plazos;
                    }
                    break;
                default:
                    break;
            }
        }

        /// <summary>
        /// EVENTO PARA CARGAR EL REPEATER CON LOS INTEGRANTES DEL GRUPO
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void ddl_Cliente_SelectedIndexChanged(object sender, EventArgs e)
        {
            Clientes Cliente = new Clientes
            {
                Id = int.Parse(ddl_Cliente.SelectedValue)
            };
            r_Documentos.DataSource = Cliente.ConsultarTodo();
            r_Documentos.DataBind();
        }

        /// <summary>
        /// EVENTO PARA SOLICITAR EL PRESTAMO
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void b_Crear_Click(object sender, EventArgs e)
        {
            if (ValidarInformacion())
            {
                try
                {
                    Crear();
                }
                catch (Exception ex)
                {
                    Literal literal = (Literal)Master.FindControl("lAlerta");
                    literal.Text = Herramientas.Alerta("Oops, ocurrió un error!", ex.Message, 4);
                }
            }
        }

        //EVENTO PARA AUTORIZAR EL PRESTAMO
        protected void bAutorizar_Click(object sender, EventArgs e)
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            int ID = int.Parse(Request.QueryString["id"].ToString());
            App_Code.Entidades.PrestamosIndividuales Prestamo = new App_Code.Entidades.PrestamosIndividuales
            {
                Id = ID,
                IdUsuario = IDUsuarioActual
            };
            Prestamo.ConsultarID();
            Prestamo.CantidadOtorgada = float.Parse(tb_CantidadAOtorgar.Text);
            Prestamo.Interes = float.Parse(tb_Interes.Text.Replace("%", ""));
            Prestamo.Anticipo = (Prestamo.CantidadOtorgada * float.Parse("0.10"));
            Prestamo.IdEstatus = 4;
            Prestamo.IdUsuario = IDUsuarioActual;
            Prestamo.Actualizar();
            int CantidadPagos = int.Parse(ddl_Plazos.SelectedValue);

            App_Code.Entidades.Pagos Pago = new App_Code.Entidades.Pagos
            {
                IdPrestamo = ID,
                IdTipoPrestamo = 1,
            };
            Clientes Cliente = new Clientes
            {
                Id = Prestamo.IdCliente
            };
            Cliente.ConsultarID();
            Pago.MontoAPagar = ((Prestamo.CantidadOtorgada * (1 + (Prestamo.Interes / 100))) - Prestamo.Anticipo) / CantidadPagos;
            for (int i = 1; i <= CantidadPagos; i++)
            {
                Pago.Plazo = i.ToString() + "/" + CantidadPagos.ToString();
                int Plazo = int.Parse(Pago.Plazo.Substring(0, Pago.Plazo.IndexOf('/')));
                Pago.FechaProgramada = DateTime.Now.AddDays(Plazo * 30);
                Pago.IdCliente = Cliente.Id;
                Pago.IdUsuario = IDUsuarioActual;
                Pago.IdEstatus = 1;
                Pago.Agregar();
            }
            Session["Alerta"] = Herramientas.Alerta("Operación existosa!", "Préstamo autorizado correctamente.", 3);
            Response.Redirect("Listado.aspx");
        }

        //EVENTO PARA ENTREGAR EL PRÉSTAMO
        protected void bEntregar_Click(object sender, EventArgs e)
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            int ID = int.Parse(Request.QueryString["id"].ToString());
            App_Code.Entidades.PrestamosIndividuales Prestamo = new App_Code.Entidades.PrestamosIndividuales
            {
                Id = ID,
                IdUsuario = IDUsuarioActual
            };
            Prestamo.ConsultarID();
            Prestamo.IdEstatus = 5;
            Prestamo.IdUsuario = IDUsuarioActual;
            Prestamo.FechaEntrega = DateTime.Now;
            Prestamo.Actualizar();

            App_Code.Entidades.Pagos Pagos = new App_Code.Entidades.Pagos
            {
                IdPrestamo = ID,
                IdTipoPrestamo = 1
            };
            using (DataSet dsPagos = Pagos.ConsultarTodo())
            {
                foreach (DataRow Fila in dsPagos.Tables[0].Rows)
                {
                    App_Code.Entidades.Pagos Pago = new App_Code.Entidades.Pagos
                    {
                        Id = int.Parse(Fila["id"].ToString()),
                        IdUsuario = IDUsuarioActual
                    };
                    Pago.ConsultarID();
                    Pago.IdEstatus = 7;
                    Pago.Actualizar();
                }
            }
            Session["Alerta"] = Herramientas.Alerta("Operación existosa!", "Préstamo entregado correctamente.", 3);
            Response.Redirect("Listado.aspx");
        }

        /// <summary>
        /// MÉTODO PARA CREAR AL PROMOTOR
        /// </summary>
        private void Crear()
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            //PROMOTOR
            App_Code.Entidades.PrestamosIndividuales Prestamo = new App_Code.Entidades.PrestamosIndividuales(IDUsuarioActual);
            PrestamoFormulario(Prestamo);
            Prestamo.Agregar();
            //AVAL
            ReferenciasPrestamos Aval = new ReferenciasPrestamos(IDUsuarioActual);
            AvalFormulario(Aval);
            Aval.IdPrestamo = Prestamo.Id;
            Aval.Agregar();
            //REDES SOCIALES DEL AVAL
            GuardarRedSocial(Aval.Id, 1, tb_aval_Facebook.Text);
            GuardarRedSocial(Aval.Id, 2, tb_aval_Twitter.Text);
            GuardarRedSocial(Aval.Id, 3, tb_aval_Instagram.Text);
            //DOCUMENTOS DEL AVAL
            GuardarDocumentos(Aval.Id, 1, fu_aval_ActaNacimiento.FileName);
            GuardarDocumentos(Aval.Id, 2, fu_aval_INE.FileName);
            GuardarDocumentos(Aval.Id, 3, fu_aval_CURP.FileName);
            GuardarDocumentos(Aval.Id, 4, fu_aval_ConstanciaResidencia.FileName);
            GuardarDocumentos(Aval.Id, 5, fu_aval_ComprobanteDomicilio.FileName);

            //PRIMER REFERENCIA
            ReferenciasPrestamos Primera = new ReferenciasPrestamos(IDUsuarioActual);
            PrimerReferenciaFormulario(Primera);
            Primera.IdPrestamo = Prestamo.Id;
            Primera.Agregar();
            //REDES SOCIALES DE LA PRIMER REFERENCIA
            GuardarRedSocial(Primera.Id, 1, tb_pr_Facebook.Text);
            GuardarRedSocial(Primera.Id, 2, tb_pr_Twitter.Text);
            GuardarRedSocial(Primera.Id, 3, tb_pr_Instagram.Text);
            //DOCUMENTOS DE LA PRIMER REFERENCIA
            GuardarDocumentos(Primera.Id, 1, fu_pr_ActaNacimiento.FileName);
            GuardarDocumentos(Primera.Id, 2, fu_pr_INE.FileName);
            GuardarDocumentos(Primera.Id, 3, fu_pr_CURP.FileName);
            GuardarDocumentos(Primera.Id, 4, fu_pr_ConstanciaResidencia.FileName);
            GuardarDocumentos(Primera.Id, 5, fu_pr_ComprobanteDomicilio.FileName);

            if (tb_sr_Nombre.Text != string.Empty)
            {
                //SEGUNDA REFERENCIA
                ReferenciasPrestamos Segunda = new ReferenciasPrestamos(IDUsuarioActual);
                SegundaReferenciaFormulario(Segunda);
                Segunda.IdPrestamo = Prestamo.Id;
                Segunda.Agregar();
                //REDES SOCIALES DE LA SEGUNDA REFERENCIA
                GuardarRedSocial(Segunda.Id, 1, tb_sr_Facebook.Text);
                GuardarRedSocial(Segunda.Id, 2, tb_sr_Twitter.Text);
                GuardarRedSocial(Segunda.Id, 3, tb_sr_Instagram.Text);
                //DOCUMENTOS DE LA SEGUNDA REFERENCIA
                GuardarDocumentos(Segunda.Id, 1, fu_sr_ActaNacimiento.FileName);
                GuardarDocumentos(Segunda.Id, 2, fu_sr_INE.FileName);
                GuardarDocumentos(Segunda.Id, 3, fu_sr_CURP.FileName);
                GuardarDocumentos(Segunda.Id, 4, fu_sr_ConstanciaResidencia.FileName);
                GuardarDocumentos(Segunda.Id, 5, fu_sr_ComprobanteDomicilio.FileName);
            }

            Session["Alerta"] = Herramientas.Alerta("Operación existosa!", "Préstamo solicitado correctamente.", 3);
            Response.Redirect("Listado.aspx");
        }

        /// <summary>
        /// MÉTODO PARA OBTENER EL PRÉSTAMO COMO RESULTADO DE LO INGRESADO EN EL FORMULARIO
        /// </summary>
        /// <param name="Prestamo"></param>
        private void PrestamoFormulario(App_Code.Entidades.PrestamosIndividuales Prestamo)
        {
            Prestamo.IdCliente = int.Parse(ddl_Cliente.SelectedValue);
            Prestamo.Motivo = tb_Motivo.Text;
            Prestamo.CantidadSolicitada = float.Parse(tb_CantidadSolicitada.Text);
            Prestamo.Garantia = tb_Garantia.Text;
            Prestamo.Observaciones = tb_Observaciones.Text;
            Prestamo.IdEstatus = 3;
        }

        /// <summary>
        /// MÉTODO PARA OBTENER EL AVAL COMO RESULTADO DE LO INGRESADO EN EL FORMULARIO
        /// </summary>
        /// <param name="aval"></param>
        private void AvalFormulario(ReferenciasPrestamos Aval)
        {
            Aval.Nombre = tb_aval_Nombre.Text;
            Aval.IdTipoPrestamo = 1;
            Aval.IdTipoReferencia = 2;
            Aval.RFC = tb_aval_RFC.Text;
            Aval.CURP = tb_aval_CURP.Text;
            Aval.FechaNacimiento = DateTime.Parse(tb_aval_FechaNacimiento.Text);
            Aval.ClaveElector = tb_aval_ClaveElector.Text;
            Aval.Direccion = tb_aval_Direccion.Text;
            Aval.ReferenciaDireccion = tb_aval_DireccionReferencia.Text;
            Aval.TelefonoCasa = tb_aval_TelefonoCasa.Text;
            Aval.TelefonoCelular = tb_aval_TelefonoCelular.Text;
            Aval.CorreoElectronico = tb_aval_CorreoElectronico.Text;
            Aval.Parentesco = tb_aval_Parentesco.Text;
            if (fu_aval_Foto.HasFile)
            {
                Aval.URLFoto = fu_aval_Foto.FileName;
            }
            Aval.Empresa = tb_aval_NombreEmpresa.Text;
            Aval.PuestoEmpresa = tb_aval_PuestoEmpresa.Text;
            Aval.DireccionEmpresa = tb_aval_DireccionEmpresa.Text;
            Aval.AntiguedadEmpresa = tb_aval_AntiguedadEmpresa.Text;
            Aval.TelefonoEmpresa = tb_aval_TelefonoEmpresa.Text;
            Aval.NombreJefe = tb_aval_NombreJefeEmpresa.Text;
        }

        /// <summary>
        /// FUNCIÓN PARA OBTENER LA PRIMER REFERENCIA COMO RESULTADO DE LO INGRESADO EN EL FORMULARIO
        /// </summary>
        /// <returns></returns>
        private void PrimerReferenciaFormulario(ReferenciasPrestamos PrimerReferencia)
        {
            PrimerReferencia.Nombre = tb_pr_Nombre.Text;
            PrimerReferencia.IdTipoPrestamo = 1;
            PrimerReferencia.IdTipoReferencia = 1;
            PrimerReferencia.RFC = tb_pr_RFC.Text;
            PrimerReferencia.CURP = tb_pr_CURP.Text;
            PrimerReferencia.FechaNacimiento = DateTime.Parse(tb_pr_FechaNacimiento.Text);
            PrimerReferencia.ClaveElector = tb_pr_ClaveElector.Text;
            PrimerReferencia.Direccion = tb_pr_Direccion.Text;
            PrimerReferencia.ReferenciaDireccion = tb_pr_DireccionReferencia.Text;
            PrimerReferencia.TelefonoCasa = tb_pr_TelefonoCasa.Text;
            PrimerReferencia.TelefonoCelular = tb_pr_TelefonoCelular.Text;
            PrimerReferencia.CorreoElectronico = tb_pr_CorreoElectronico.Text;
            if (fu_pr_Foto.HasFile)
            {
                PrimerReferencia.URLFoto = fu_pr_Foto.FileName;
            }
        }

        /// <summary>
        /// FUNCIÓN PARA OBTENER LA SEGUNDA REFERENCIA COMO RESULTADO DE LO INGRESADO EN EL FORMULARIO
        /// </summary>
        /// <returns></returns>
        private void SegundaReferenciaFormulario(ReferenciasPrestamos SegundaReferencia)
        {
            SegundaReferencia.Nombre = tb_sr_Nombre.Text;
            SegundaReferencia.IdTipoPrestamo = 1;
            SegundaReferencia.IdTipoReferencia = 1;
            SegundaReferencia.RFC = tb_sr_RFC.Text;
            SegundaReferencia.Direccion = tb_sr_Direccion.Text;
            SegundaReferencia.TelefonoCasa = tb_sr_TelefonoCasa.Text;
            SegundaReferencia.TelefonoCelular = tb_sr_TelefonoCelular.Text;
            SegundaReferencia.CorreoElectronico = tb_sr_CorreoElectronico.Text;
            SegundaReferencia.FechaNacimiento = DateTime.Parse(tb_sr_FechaNacimiento.Text);
            SegundaReferencia.CURP = tb_sr_CURP.Text;
            SegundaReferencia.ClaveElector = tb_sr_ClaveElector.Text;
            if (fu_sr_Foto.HasFile)
            {
                SegundaReferencia.URLFoto = fu_sr_Foto.FileName;
            }
        }

        /// <summary>
        /// MÉTODO PARA GUARDAR LAS REDES SOCIALES
        /// </summary>
        /// <param name="id"></param>
        /// <param name="idTipoRed"></param>
        /// <param name="URL"></param>
        private void GuardarRedSocial(int? id, int idTipoRed, string URL)
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            RedesSociales Red = new RedesSociales
            {
                IdActor = id,
                IdTipoActor = 7,
                IdTipoRedSocial = idTipoRed,
                IdUsuario = IDUsuarioActual
            };
            Red.ConsultarID();
            Red.URL = URL;
            if (Red.Id == null)
            {
                Red.Agregar();
            }
            else
            {
                Red.Actualizar();
            }
        }

        /// <summary>
        /// MÉTODO PARA GUARDAR LOS DOCUMENTOS
        /// </summary>
        /// <param name="id"></param>
        /// <param name="idTipoDocumento"></param>
        /// <param name="URL"></param>
        private void GuardarDocumentos(int? id, int idTipoDocumento, string URL)
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            Documentos Documento = new Documentos
            {
                IdTipoActor = 7,
                IdActor = id,
                IdTipoDocumento = idTipoDocumento,
                IdUsuario = IDUsuarioActual
            };
            Documento.ConsultarID();
            Documento.URLDocumento = URL;
            if (Documento.Id == null)
            {
                Documento.Agregar();
            }
            else
            {
                if (URL != string.Empty)
                {
                    Documento.Actualizar();
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
                DateTime fechaNacimiento = DateTime.Parse(tb_aval_FechaNacimiento.Text);
                DateTime pr_fechaNacimiento = DateTime.Parse(tb_pr_FechaNacimiento.Text);
                if (tb_sr_Nombre.Text != string.Empty)
                {
                    DateTime sr_fechaNacimiento = DateTime.Parse(tb_sr_FechaNacimiento.Text);
                }
                if (Request.QueryString["id"] == null)
                {
                    if (!fu_aval_Foto.HasFile)
                    {
                        throw new Exception("Por favor, ingrese la foto del aval.");
                    }
                    /*DOCUMENTOS DEL AVAL*/
                    if (!fu_aval_ActaNacimiento.HasFile)
                    {
                        throw new Exception("Por favor, ingrese el acta de nacimiento del aval.");
                    }
                    if (!fu_aval_ConstanciaResidencia.HasFile)
                    {
                        throw new Exception("Por favor, ingrese la constancia de residencia del aval.");
                    }
                    if (!fu_aval_CURP.HasFile)
                    {
                        throw new Exception("Por favor, ingrese el CURP del aval.");
                    }
                    if (!fu_aval_INE.HasFile)
                    {
                        throw new Exception("Por favor, ingrese el INE del aval.");
                    }
                    if (!fu_aval_ComprobanteDomicilio.HasFile)
                    {
                        throw new Exception("Por favor, ingrese el comprobante de domicilio del aval.");
                    }
                    /*/DOCUMENTOS DEL AVAL*/
                    /*DOCUMENTOS DE LA PRIMER REFERENCIA*/
                    if (!fu_pr_ActaNacimiento.HasFile)
                    {
                        throw new Exception("Por favor, ingrese el acta de nacimiento de la primer referencia.");
                    }
                    if (!fu_pr_ConstanciaResidencia.HasFile)
                    {
                        throw new Exception("Por favor, ingrese la constancia de residencia de la primer referencia.");
                    }
                    if (!fu_pr_CURP.HasFile)
                    {
                        throw new Exception("Por favor, ingrese el CURP de la primer referencia.");
                    }
                    if (!fu_pr_INE.HasFile)
                    {
                        throw new Exception("Por favor, ingrese el INE de la primer referencia.");
                    }
                    if (!fu_pr_ComprobanteDomicilio.HasFile)
                    {
                        throw new Exception("Por favor, ingrese el comprobante de domicilio de la primer referencia.");
                    }
                    /*/DOCUMENTOS DE LA PRIMER REFERENCIA*/
                    if (tb_sr_Nombre.Text != string.Empty)
                    {
                        /*DOCUMENTOS DE LA SEGUNDA REFERENCIA*/
                        if (!fu_sr_ActaNacimiento.HasFile)
                        {
                            throw new Exception("Por favor, ingrese el acta de nacimiento de la segunda referencia.");
                        }
                        if (!fu_sr_ConstanciaResidencia.HasFile)
                        {
                            throw new Exception("Por favor, ingrese la constancia de residencia de la segunda referencia.");
                        }
                        if (!fu_sr_CURP.HasFile)
                        {
                            throw new Exception("Por favor, ingrese el CURP de la segunda referencia.");
                        }
                        if (!fu_sr_INE.HasFile)
                        {
                            throw new Exception("Por favor, ingrese el INE de la segunda referencia.");
                        }
                        if (!fu_sr_ComprobanteDomicilio.HasFile)
                        {
                            throw new Exception("Por favor, ingrese el comsrobante de domicilio de la segunda referencia.");
                        }
                    }
                    /*/DOCUMENTOS DE LA SEGUNDA REFERENCIA*/
                }
                return true;
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