using DiazFu.App_Code.Entidades;
using DiazFu.App_Code.Utilerias;
using System;
using System.Data;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DiazFu.Modules.Prestamos.PrestamosGrupales
{
    public partial class Captura : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            CargaControles();
            if (Request.QueryString["id"] != null)
            {
                ConsultarPrestamo();
            }
        }

        /// <summary>
        /// CARGA DE LOS CONTROLES
        /// </summary>
        private void CargaControles()
        {
            //CARGA DE LOS GRUPOS
            Grupos Grupos = new Grupos();
            ddl_Grupo.DataSource = Grupos.ConsultarTodo();
            ddl_Grupo.DataTextField = "Nombre";
            ddl_Grupo.DataValueField = "Id";
            ddl_Grupo.DataBind();
            //CARGA DEL REPEATER
            IntegrantesGrupos Integrantes = new IntegrantesGrupos
            {
                IdGrupo = int.Parse(ddl_Grupo.SelectedValue)
            };
            r_Documentos.DataSource = Integrantes.ConsultarTodo();
            r_Documentos.DataBind();
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
                    IdActor = int.Parse(((DataRowView)e.Item.DataItem).Row.ItemArray[3].ToString()),
                    IdTipoActor = 3
                };
                GridView Grid = (GridView)e.Item.FindControl("gvDocumentos");
                Grid.DataSource = Documentos.ConsultarTodo();
                Grid.DataBind();
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
            App_Code.Entidades.PrestamosGrupales Prestamo = new App_Code.Entidades.PrestamosGrupales
            {
                Id = int.Parse(Request.QueryString["Id"].ToString())
            };
            Prestamo.ConsultarID();
            ddl_Grupo.SelectedValue = Prestamo.IdGrupo.ToString();
            tb_Motivo.Text = Prestamo.Motivo;
            tb_CantidadSolicitada.Text = Prestamo.CantidadSolicitada.ToString();
            tb_Garantia.Text = Prestamo.Garantia;
            tb_Observaciones.Text = Prestamo.Observaciones;
            CargaControles();

            int IdTipoActor = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).IdTipoActor.ToString(), out IdTipoActor);
            if (IdTipoActor != 1 && Prestamo.IdEstatus == 3)
            {
                Response.Redirect("~/Modules/Prestamos/PrestamosGrupales/Listado.aspx");
            }
            #endregion

            //CONSULTA DE AVAL
            //-------------------------------------------------------------------------------------------------
            //AVAL
            #region Aval
            ReferenciasPrestamos Aval = new ReferenciasPrestamos
            {
                IdPrestamo = Prestamo.Id,
                IdTipoReferencia = 2
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
                IdTipoReferencia = 1
            };
            DataSet Consulta = Referencias.ConsultarTodo();
            //PRIMER REFERENCIA
            #region Primer Referencia
            ReferenciasPrestamos PrimerReferencia = new ReferenciasPrestamos
            {
                IdPrestamo = Prestamo.Id,
                IdTipoReferencia = 1
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
            PrimerReferenciaFacebook.ConsultarRedesSocialesReferenciasPromotores();
            tb_pr_Facebook.Text = PrimerReferenciaFacebook.URL;
            RedesSociales PrimerReferenciaTwitter = new RedesSociales
            {
                IdActor = PrimerReferencia.Id,
                IdTipoActor = 7,
                IdTipoRedSocial = 2
            };
            PrimerReferenciaTwitter.ConsultarRedesSocialesReferenciasPromotores();
            tb_pr_Twitter.Text = PrimerReferenciaTwitter.URL;
            RedesSociales PrimerReferenciaInstagram = new RedesSociales
            {
                IdActor = PrimerReferencia.Id,
                IdTipoActor = 7,
                IdTipoRedSocial = 3
            };
            PrimerReferenciaInstagram.ConsultarRedesSocialesReferenciasPromotores();
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
                    Id = int.Parse(Consulta.Tables[0].Rows[1]["Id"].ToString())
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
                SegundaReferenciaFacebook.ConsultarRedesSocialesReferenciasPromotores();
                tb_sr_Facebook.Text = SegundaReferenciaFacebook.URL;
                RedesSociales SegundaReferenciaTwitter = new RedesSociales
                {
                    IdActor = SegundaReferencia.Id,
                    IdTipoActor = 5,
                    IdTipoRedSocial = 2
                };
                SegundaReferenciaTwitter.ConsultarRedesSocialesReferenciasPromotores();
                tb_sr_Twitter.Text = SegundaReferenciaTwitter.URL;
                RedesSociales SegundaReferenciaInstagram = new RedesSociales
                {
                    IdActor = SegundaReferencia.Id,
                    IdTipoActor = 5,
                    IdTipoRedSocial = 3
                };
                SegundaReferenciaInstagram.ConsultarRedesSocialesReferenciasPromotores();
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

            //HABILITAR PANEL DE AUTORIZACION
            jtAutorizacion.Visible = true;
        }

        /// <summary>
        /// EVENTO PARA CARGAR EL REPEATER CON LOS INTEGRANTES DEL GRUPO
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void ddl_Grupo_SelectedIndexChanged(object sender, EventArgs e)
        {
            IntegrantesGrupos Integrantes = new IntegrantesGrupos
            {
                IdGrupo = int.Parse(ddl_Grupo.SelectedValue)
            };
            r_Documentos.DataSource = Integrantes.ConsultarTodo();
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
            App_Code.Entidades.PrestamosGrupales Prestamo = new App_Code.Entidades.PrestamosGrupales
            {
                Id = ID,
                IdUsuario = IDUsuarioActual
            };
            Prestamo.ConsultarID();
            Prestamo.CantidadOtorgada = float.Parse(tb_CantidadAOtorgar.Text);
            Prestamo.Interes = 7;
            Prestamo.IdEstatus = 4;
            
        }

        ///// <summary>
        ///// MÉTODO PARA ACTUALIZAR AL PRESTAMO
        ///// </summary>
        //private void Actualizar()
        //{
        //    int IDUsuarioActual = 0;
        //    int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
        //    //PROMOTOR
        //    int ID = int.Parse(Request.QueryString["id"].ToString());
        //    App_Code.Entidades.PrestamosGrupales Prestamo = new App_Code.Entidades.PrestamosGrupales
        //    {
        //        Id = ID,
        //        IdUsuario = IDUsuarioActual
        //    };
        //    Prestamo.ConsultarID();
        //    PromotorFormulario(Prestamo);
        //    Prestamo.Actualizar();
        //    //USUARIO
        //    Usuarios Usuario = new Usuarios()
        //    {
        //        IdActor = Prestamo.Id,
        //        IdTipoActor = 2,
        //        IdUsuario = IDUsuarioActual
        //    };
        //    Usuario.ConsultarID();
        //    Usuario.Nombre = tb_Usuario.Text;
        //    Usuario.Contrasena = tb_Contrasena.Text;
        //    Usuario.Actualizar();
        //    //REDES SOCIALES DEL PROMOTOR
        //    GuardarRedSocial(Prestamo.Id, 2, 1, tb_Facebook.Text);
        //    GuardarRedSocial(Prestamo.Id, 2, 2, tb_Twitter.Text);
        //    GuardarRedSocial(Prestamo.Id, 2, 3, tb_Instagram.Text);
        //    //DOCUMENTOS DEL PROMOTOR
        //    GuardarDocumentos(Prestamo.Id, 2, 1, fu_ActaNacimiento.FileName);
        //    GuardarDocumentos(Prestamo.Id, 2, 2, fu_INE.FileName);
        //    GuardarDocumentos(Prestamo.Id, 2, 3, fu_CURP.FileName);
        //    GuardarDocumentos(Prestamo.Id, 2, 4, fu_ConstanciaResidencia.FileName);
        //    GuardarDocumentos(Prestamo.Id, 2, 5, fu_ComprobanteDomicilio.FileName);

        //    //PRIMER REFERENCIA
        //    ReferenciasPromotores Primera = new ReferenciasPromotores
        //    {
        //        IdActor = Prestamo.Id,
        //        IdUsuario = IDUsuarioActual
        //    };
        //    DataSet Consulta = Primera.ConsultarTodo();
        //    Primera.Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
        //    Primera.ConsultarID();
        //    PrimerReferenciaFormulario(Primera);
        //    Primera.Actualizar();
        //    //REDES SOCIALES DE LA PRIMER REFERENCIA
        //    GuardarRedSocial(Primera.Id, 5, 1, tb_pr_Facebook.Text);
        //    GuardarRedSocial(Primera.Id, 5, 2, tb_pr_Twitter.Text);
        //    GuardarRedSocial(Primera.Id, 5, 3, tb_pr_Instagram.Text);
        //    //DOCUMENTOS DE LA PRIMER REFERENCIA
        //    GuardarDocumentos(Primera.Id, 5, 1, fu_pr_ActaNacimiento.FileName);
        //    GuardarDocumentos(Primera.Id, 5, 2, fu_pr_INE.FileName);
        //    GuardarDocumentos(Primera.Id, 5, 3, fu_pr_CURP.FileName);
        //    GuardarDocumentos(Primera.Id, 5, 4, fu_pr_ConstanciaResidencia.FileName);
        //    GuardarDocumentos(Primera.Id, 5, 5, fu_pr_ComprobanteDomicilio.FileName);

        //    //SEGUNDA REFERENCIA
        //    ReferenciasPromotores Segunda = new ReferenciasPromotores
        //    {
        //        IdActor = Prestamo.Id,
        //        IdUsuario = IDUsuarioActual
        //    };
        //    Segunda.Id = int.Parse(Consulta.Tables[0].Rows[1]["Id"].ToString());
        //    Segunda.ConsultarID();
        //    SegundaReferenciaFormulario(Segunda);
        //    Segunda.Actualizar();
        //    //REDES SOCIALES DE LA SEGUNDA REFERENCIA
        //    GuardarRedSocial(Segunda.Id, 5, 1, tb_sr_Facebook.Text);
        //    GuardarRedSocial(Segunda.Id, 5, 2, tb_sr_Twitter.Text);
        //    GuardarRedSocial(Segunda.Id, 5, 3, tb_sr_Instagram.Text);
        //    //DOCUMENTOS DE LA SEGUNDA REFERENCIA
        //    GuardarDocumentos(Segunda.Id, 5, 1, fu_sr_ActaNacimiento.FileName);
        //    GuardarDocumentos(Segunda.Id, 5, 2, fu_sr_INE.FileName);
        //    GuardarDocumentos(Segunda.Id, 5, 3, fu_sr_CURP.FileName);
        //    GuardarDocumentos(Segunda.Id, 5, 4, fu_sr_ConstanciaResidencia.FileName);
        //    GuardarDocumentos(Segunda.Id, 5, 5, fu_sr_ComprobanteDomicilio.FileName);

        //    Session["Alerta"] = Herramientas.Alerta("Operación existosa!", "Promotor actualizado correctamente.", 3);
        //    Response.Redirect("Listado.aspx");
        //}

        /// <summary>
        /// MÉTODO PARA CREAR AL PROMOTOR
        /// </summary>
        private void Crear()
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            //PROMOTOR
            App_Code.Entidades.PrestamosGrupales Prestamo = new App_Code.Entidades.PrestamosGrupales(IDUsuarioActual);
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
        private void PrestamoFormulario(App_Code.Entidades.PrestamosGrupales Prestamo)
        {
            Prestamo.IdGrupo = int.Parse(ddl_Grupo.SelectedValue);
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
            Aval.IdTipoPrestamo = 2;
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
            PrimerReferencia.IdTipoPrestamo = 2;
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
            SegundaReferencia.IdTipoPrestamo = 2;
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