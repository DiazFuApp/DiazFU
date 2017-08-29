using DiazFu.App_Code.Utilerias;
using System;
using System.Web.UI.WebControls;
using DiazFu.App_Code.Entidades;
using System.Data;

namespace DiazFu.Modules.Administracion.Promotores
{
    public partial class Captura : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Request.QueryString["id"] != null)
                {
                    ConsultarPromotor();
                }
            }
        }

        /// <summary>
        /// MÉTODO PARA CONSULTAR UN PROMOTOR A EDITAR
        /// </summary>
        private void ConsultarPromotor()
        {
            int ID = int.Parse(Request.QueryString["id"].ToString());
            App_Code.Entidades.Promotores Promotor = new App_Code.Entidades.Promotores
            {
                Id = ID
            };
            Promotor.ConsultarID();

            //LLENAR FORMULARIO
            tb_Nombre.Text = Promotor.Nombre;
            tb_RFC.Text = Promotor.RFC;
            tb_Direccion.Text = Promotor.Direccion;
            tb_TelefonoCasa.Text = Promotor.TelefonoCasa;
            tb_TelefonoCelular.Text = Promotor.TelefonoCelular;
            tb_CorreoElectronico.Text = Promotor.CorreoElectronico;
            tb_FechaNacimiento.Text = Promotor.FechaNacimiento.ToString();
            tb_CURP.Text = Promotor.CURP;
            tb_ClaveElector.Text = Promotor.ClaveElector;
            div_Foto.Visible = true;
            a_Foto.HRef = Promotor.URLFoto;
            fu_Foto.Attributes.Remove("required");
            //-------------------------------------------------------------------------------------------------
            //REDES
            RedesSociales PromotorFacebook = new RedesSociales
            {
                IdActor = Promotor.Id,
                IdTipoActor = 2,
                IdTipoRedSocial = 1
            };
            PromotorFacebook.ConsultarID();
            tb_Facebook.Text = PromotorFacebook.URL;
            RedesSociales PromotorTwitter = new RedesSociales
            {
                IdActor = Promotor.Id,
                IdTipoActor = 2,
                IdTipoRedSocial = 2
            };
            PromotorTwitter.ConsultarID();
            tb_Twitter.Text = PromotorTwitter.URL;
            RedesSociales PromotorInstagram = new RedesSociales
            {
                IdActor = Promotor.Id,
                IdTipoActor = 2,
                IdTipoRedSocial = 2
            };
            PromotorInstagram.ConsultarID();
            tb_Instagram.Text = PromotorInstagram.URL;
            //-------------------------------------------------------------------------------------------------
            //DOCUMENTOS
            Documentos PromotorActaNacimiento = new Documentos
            {
                IdTipoActor = 2,
                IdActor = Promotor.Id,
                IdTipoDocumento = 1
            };
            PromotorActaNacimiento.ConsultarID();
            a_ActaNacimiento.HRef = PromotorActaNacimiento.URLDocumento;
            div_ActaNacimiento.Visible = true;
            fu_ActaNacimiento.Attributes.Remove("required");
            Documentos PromotorConstanciaResidencia = new Documentos
            {
                IdTipoActor = 2,
                IdActor = Promotor.Id,
                IdTipoDocumento = 4
            };
            PromotorConstanciaResidencia.ConsultarID();
            a_ConstanciaResidencia.HRef = PromotorConstanciaResidencia.URLDocumento;
            div_ConstanciaResidencia.Visible = true;
            fu_ConstanciaResidencia.Attributes.Remove("required");
            Documentos PromotorCURP = new Documentos
            {
                IdTipoActor = 2,
                IdActor = Promotor.Id,
                IdTipoDocumento = 3
            };
            PromotorCURP.ConsultarID();
            a_CURP.HRef = PromotorCURP.URLDocumento;
            div_CURP.Visible = true;
            fu_CURP.Attributes.Remove("required");
            Documentos PromotorINE = new Documentos
            {
                IdTipoActor = 2,
                IdActor = Promotor.Id,
                IdTipoDocumento = 2
            };
            PromotorINE.ConsultarID();
            a_INE.HRef = PromotorINE.URLDocumento;
            div_INE.Visible = true;
            fu_INE.Attributes.Remove("required");
            Documentos PromotorComprobanteDomicilio = new Documentos
            {
                IdTipoActor = 2,
                IdActor = Promotor.Id,
                IdTipoDocumento = 5
            };
            PromotorComprobanteDomicilio.ConsultarID();
            a_ComprobanteDomicilio.HRef = PromotorComprobanteDomicilio.URLDocumento;
            div_ComprobanteDomicilio.Visible = true;
            fu_ComprobanteDomicilio.Attributes.Remove("required");
            //-------------------------------------------------------------------------------------------------
            //REFERENCIA 1
            ReferenciasPromotores Referencias = new ReferenciasPromotores
            {
                IdActor = Promotor.Id
            };
            DataSet Consulta = Referencias.ConsultarTodo();

            ReferenciasPromotores PrimerReferencia = new ReferenciasPromotores
            {
                Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString())
            };
            PrimerReferencia.ConsultarID();
            tb_pr_Nombre.Text = PrimerReferencia.Nombre;
            tb_pr_RFC.Text = PrimerReferencia.RFC;
            tb_pr_Direccion.Text = PrimerReferencia.Direccion;
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
                IdTipoActor = 5,
                IdTipoRedSocial = 1
            };
            PrimerReferenciaFacebook.ConsultarRedesSocialesReferenciasPromotores();
            tb_pr_Facebook.Text = PrimerReferenciaFacebook.URL;
            RedesSociales PrimerReferenciaTwitter = new RedesSociales
            {
                IdActor = PrimerReferencia.Id,
                IdTipoActor = 5,
                IdTipoRedSocial = 2
            };
            PrimerReferenciaTwitter.ConsultarRedesSocialesReferenciasPromotores();
            tb_pr_Twitter.Text = PrimerReferenciaTwitter.URL;
            RedesSociales PrimerReferenciaInstagram = new RedesSociales
            {
                IdActor = PrimerReferencia.Id,
                IdTipoActor = 5,
                IdTipoRedSocial = 3
            };
            PrimerReferenciaInstagram.ConsultarRedesSocialesReferenciasPromotores();
            tb_pr_Instagram.Text = PrimerReferenciaInstagram.URL;
            //-------------------------------------------------------------------------------------------------
            //DOCUMENTOS
            Documentos PrimerReferenciaActaNacimiento = new Documentos
            {
                IdTipoActor = 5,
                IdActor = PrimerReferencia.Id,
                IdTipoDocumento = 1
            };
            PrimerReferenciaActaNacimiento.ConsultarID();
            a_pr_ActaNacimiento.HRef = PrimerReferenciaActaNacimiento.URLDocumento;
            div_pr_ActaNacimiento.Visible = true;
            fu_pr_ActaNacimiento.Attributes.Remove("required");
            Documentos PrimerReferenciaConstanciaResidencia = new Documentos
            {
                IdTipoActor = 5,
                IdActor = PrimerReferencia.Id,
                IdTipoDocumento = 4
            };
            PrimerReferenciaConstanciaResidencia.ConsultarID();
            a_pr_ConstanciaResidencia.HRef = PrimerReferenciaConstanciaResidencia.URLDocumento;
            div_pr_ConstanciaResidencia.Visible = true;
            fu_pr_ConstanciaResidencia.Attributes.Remove("required");
            Documentos PrimerReferenciaCURP = new Documentos
            {
                IdTipoActor = 5,
                IdActor = PrimerReferencia.Id,
                IdTipoDocumento = 3
            };
            PrimerReferenciaCURP.ConsultarID();
            a_pr_CURP.HRef = PrimerReferenciaCURP.URLDocumento;
            div_pr_CURP.Visible = true;
            fu_pr_CURP.Attributes.Remove("required");
            Documentos PrimerReferenciaINE = new Documentos
            {
                IdTipoActor = 5,
                IdActor = PrimerReferencia.Id,
                IdTipoDocumento = 2
            };
            PrimerReferenciaINE.ConsultarID();
            a_pr_INE.HRef = PrimerReferenciaINE.URLDocumento;
            div_pr_INE.Visible = true;
            fu_pr_INE.Attributes.Remove("required");
            Documentos PrimerReferenciaComprobanteDomicilio = new Documentos
            {
                IdTipoActor = 5,
                IdActor = PrimerReferencia.Id,
                IdTipoDocumento = 5
            };
            PrimerReferenciaComprobanteDomicilio.ConsultarID();
            a_pr_ComprobanteDomicilio.HRef = PrimerReferenciaComprobanteDomicilio.URLDocumento;
            div_pr_ComprobanteDomicilio.Visible = true;
            fu_pr_ComprobanteDomicilio.Attributes.Remove("required");
            //-------------------------------------------------------------------------------------------------
            //REFERENCIA 2
            ReferenciasPromotores SegundaReferencia = new ReferenciasPromotores
            {
                Id = int.Parse(Consulta.Tables[0].Rows[1]["Id"].ToString())
            };
            SegundaReferencia.ConsultarID();
            tb_sr_Nombre.Text = SegundaReferencia.Nombre;
            tb_sr_RFC.Text = SegundaReferencia.RFC;
            tb_sr_Direccion.Text = SegundaReferencia.Direccion;
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
            //-------------------------------------------------------------------------------------------------
            b_Crear.Text = "Acutalizar Promotor";
        }

        /// <summary>
        /// MÉTODO PARA CREAR EL PROMOTOR
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void b_Crear_Click(object sender, EventArgs e)
        {
            if (ValidarInformacion())
            {
                try
                {
                    if (Request.QueryString["id"] == null)
                    {
                        Crear();
                    }
                    else
                    {
                        Actualizar();
                    }
                }
                catch (Exception ex)
                {
                    Literal literal = (Literal)Master.FindControl("lAlerta");
                    literal.Text = Herramientas.Alerta("Oops, ocurrió un error!", ex.Message, 4);
                }
            }
        }

        /// <summary>
        /// MÉTODO PARA ACTUALIZAR AL PROMOTOR
        /// </summary>
        private void Actualizar()
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            //PROMOTOR
            int ID = int.Parse(Request.QueryString["id"].ToString());
            App_Code.Entidades.Promotores Promotor = new App_Code.Entidades.Promotores
            {
                Id = ID,
                IdUsuario = IDUsuarioActual
            };
            Promotor.ConsultarID();
            PromotorFormulario(Promotor);
            Promotor.Actualizar();
            //REDES SOCIALES DEL PROMOTOR
            GuardarRedSocial(Promotor.Id, 2, 1, tb_Facebook.Text);
            GuardarRedSocial(Promotor.Id, 2, 2, tb_Twitter.Text);
            GuardarRedSocial(Promotor.Id, 2, 3, tb_Instagram.Text);
            //DOCUMENTOS DEL PROMOTOR
            GuardarDocumentos(Promotor.Id, 2, 1, fu_ActaNacimiento.FileName);
            GuardarDocumentos(Promotor.Id, 2, 2, fu_INE.FileName);
            GuardarDocumentos(Promotor.Id, 2, 3, fu_CURP.FileName);
            GuardarDocumentos(Promotor.Id, 2, 4, fu_ConstanciaResidencia.FileName);
            GuardarDocumentos(Promotor.Id, 2, 5, fu_ComprobanteDomicilio.FileName);

            //PRIMER REFERENCIA
            ReferenciasPromotores Primera = new ReferenciasPromotores
            {
                IdActor = Promotor.Id,
                IdUsuario = IDUsuarioActual
            };
            DataSet Consulta = Primera.ConsultarTodo();
            Primera.Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
            Primera.ConsultarID();
            PrimerReferenciaFormulario(Primera);
            Primera.Actualizar();
            //REDES SOCIALES DE LA PRIMER REFERENCIA
            GuardarRedSocial(Primera.Id, 5, 1, tb_pr_Facebook.Text);
            GuardarRedSocial(Primera.Id, 5, 2, tb_pr_Twitter.Text);
            GuardarRedSocial(Primera.Id, 5, 3, tb_pr_Instagram.Text);
            //DOCUMENTOS DE LA PRIMER REFERENCIA
            GuardarDocumentos(Primera.Id, 5, 1, fu_pr_ActaNacimiento.FileName);
            GuardarDocumentos(Primera.Id, 5, 2, fu_pr_INE.FileName);
            GuardarDocumentos(Primera.Id, 5, 3, fu_pr_CURP.FileName);
            GuardarDocumentos(Primera.Id, 5, 4, fu_pr_ConstanciaResidencia.FileName);
            GuardarDocumentos(Primera.Id, 5, 5, fu_pr_ComprobanteDomicilio.FileName);

            //SEGUNDA REFERENCIA
            ReferenciasPromotores Segunda = new ReferenciasPromotores
            {
                IdActor = Promotor.Id,
                IdUsuario = IDUsuarioActual
            };
            Segunda.Id = int.Parse(Consulta.Tables[0].Rows[1]["Id"].ToString());
            Segunda.ConsultarID();
            SegundaReferenciaFormulario(Segunda);
            Segunda.Actualizar();
            //REDES SOCIALES DE LA SEGUNDA REFERENCIA
            GuardarRedSocial(Segunda.Id, 5, 1, tb_sr_Facebook.Text);
            GuardarRedSocial(Segunda.Id, 5, 2, tb_sr_Twitter.Text);
            GuardarRedSocial(Segunda.Id, 5, 3, tb_sr_Instagram.Text);
            //DOCUMENTOS DE LA SEGUNDA REFERENCIA
            GuardarDocumentos(Segunda.Id, 5, 1, fu_sr_ActaNacimiento.FileName);
            GuardarDocumentos(Segunda.Id, 5, 2, fu_sr_INE.FileName);
            GuardarDocumentos(Segunda.Id, 5, 3, fu_sr_CURP.FileName);
            GuardarDocumentos(Segunda.Id, 5, 4, fu_sr_ConstanciaResidencia.FileName);
            GuardarDocumentos(Segunda.Id, 5, 5, fu_sr_ComprobanteDomicilio.FileName);

            Session["Alerta"] = Herramientas.Alerta("Operación existosa!", "Promotor actualizado correctamente.", 3);
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
            App_Code.Entidades.Promotores Promotor = new App_Code.Entidades.Promotores(IDUsuarioActual);
            PromotorFormulario(Promotor);
            Promotor.Agregar();
            //REDES SOCIALES DEL PROMOTOR
            GuardarRedSocial(Promotor.Id, 2, 1, tb_Facebook.Text);
            GuardarRedSocial(Promotor.Id, 2, 2, tb_Twitter.Text);
            GuardarRedSocial(Promotor.Id, 2, 3, tb_Instagram.Text);
            //DOCUMENTOS DEL PROMOTOR
            GuardarDocumentos(Promotor.Id, 2, 1, fu_ActaNacimiento.FileName);
            GuardarDocumentos(Promotor.Id, 2, 2, fu_INE.FileName);
            GuardarDocumentos(Promotor.Id, 2, 3, fu_CURP.FileName);
            GuardarDocumentos(Promotor.Id, 2, 4, fu_ConstanciaResidencia.FileName);
            GuardarDocumentos(Promotor.Id, 2, 5, fu_ComprobanteDomicilio.FileName);

            //PRIMER REFERENCIA
            ReferenciasPromotores Primera = new ReferenciasPromotores(IDUsuarioActual);
            PrimerReferenciaFormulario(Primera);
            Primera.IdActor = Promotor.Id;
            Primera.Agregar();
            //REDES SOCIALES DE LA PRIMER REFERENCIA
            GuardarRedSocial(Primera.Id, 5, 1, tb_pr_Facebook.Text);
            GuardarRedSocial(Primera.Id, 5, 2, tb_pr_Twitter.Text);
            GuardarRedSocial(Primera.Id, 5, 3, tb_pr_Instagram.Text);
            //DOCUMENTOS DE LA PRIMER REFERENCIA
            GuardarDocumentos(Primera.Id, 5, 1, fu_pr_ActaNacimiento.FileName);
            GuardarDocumentos(Primera.Id, 5, 2, fu_pr_INE.FileName);
            GuardarDocumentos(Primera.Id, 5, 3, fu_pr_CURP.FileName);
            GuardarDocumentos(Primera.Id, 5, 4, fu_pr_ConstanciaResidencia.FileName);
            GuardarDocumentos(Primera.Id, 5, 5, fu_pr_ComprobanteDomicilio.FileName);

            //SEGUNDA REFERENCIA
            ReferenciasPromotores Segunda = new ReferenciasPromotores(IDUsuarioActual);
            SegundaReferenciaFormulario(Segunda);
            Segunda.IdActor = Promotor.Id;
            Segunda.Agregar();
            //REDES SOCIALES DE LA SEGUNDA REFERENCIA
            GuardarRedSocial(Segunda.Id, 5, 1, tb_sr_Facebook.Text);
            GuardarRedSocial(Segunda.Id, 5, 2, tb_sr_Twitter.Text);
            GuardarRedSocial(Segunda.Id, 5, 3, tb_sr_Instagram.Text);
            //DOCUMENTOS DE LA SEGUNDA REFERENCIA
            GuardarDocumentos(Segunda.Id, 5, 1, fu_sr_ActaNacimiento.FileName);
            GuardarDocumentos(Segunda.Id, 5, 2, fu_sr_INE.FileName);
            GuardarDocumentos(Segunda.Id, 5, 3, fu_sr_CURP.FileName);
            GuardarDocumentos(Segunda.Id, 5, 4, fu_sr_ConstanciaResidencia.FileName);
            GuardarDocumentos(Segunda.Id, 5, 5, fu_sr_ComprobanteDomicilio.FileName);

            Session["Alerta"] = Herramientas.Alerta("Operación existosa!", "Promotor creado correctamente.", 3);
            Response.Redirect("Listado.aspx");
        }

        /// <summary>
        /// MÉTODO PARA GUARDAR LOS DOCUMENTOS
        /// </summary>
        /// <param name="id"></param>
        /// <param name="idTipoActor"></param>
        /// <param name="idTipoDocumento"></param>
        /// <param name="URL"></param>
        private void GuardarDocumentos(int? id, int idTipoActor, int idTipoDocumento, string URL)
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            Documentos Documento = new Documentos
            {
                IdTipoActor = idTipoActor,
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
        /// MÉTODO PARA GUARDAR LAS REDES SOCIALES
        /// </summary>
        /// <param name="id"></param>
        /// <param name="idTipoActor"></param>
        /// <param name="idTipoRed"></param>
        /// <param name="URL"></param>
        private void GuardarRedSocial(int? id, int idTipoActor, int idTipoRed, string URL)
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            RedesSociales Red = new RedesSociales
            {
                IdActor = id,
                IdTipoActor = idTipoActor,
                IdTipoRedSocial = idTipoRed,
                IdUsuario = IDUsuarioActual
            };
            if (Red.IdTipoActor == 5)
            {
                Red.ConsultarRedesSocialesReferenciasPromotores();
            }
            else
            {
                Red.ConsultarID();
            }
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
        /// MÉTODO PARA OBTENER EL PROMOTOR COMO RESULTADO DE LO INGRESADO EN EL FORMULARIO
        /// </summary>
        /// <returns></returns>
        private void PromotorFormulario(App_Code.Entidades.Promotores Promotor)
        {
            Promotor.Nombre = tb_Nombre.Text;
            Promotor.RFC = tb_RFC.Text;
            Promotor.Direccion = tb_Direccion.Text;
            Promotor.TelefonoCasa = tb_TelefonoCasa.Text;
            Promotor.TelefonoCelular = tb_TelefonoCelular.Text;
            Promotor.CorreoElectronico = tb_CorreoElectronico.Text;
            Promotor.FechaNacimiento = DateTime.Parse(tb_FechaNacimiento.Text);
            Promotor.CURP = tb_CURP.Text;
            Promotor.ClaveElector = tb_ClaveElector.Text;
            if (fu_Foto.HasFile)
            {
                Promotor.URLFoto = fu_Foto.FileName;
            }
        }

        /// <summary>
        /// FUNCIÓN PARA OBTENER LA PRIMER REFERENCIA COMO RESULTADO DE LO INGRESADO EN EL FORMULARIO
        /// </summary>
        /// <returns></returns>
        private void PrimerReferenciaFormulario(ReferenciasPromotores PrimerReferencia)
        {
            PrimerReferencia.Nombre = tb_pr_Nombre.Text;
            PrimerReferencia.IdTipoReferencia = 1;
            PrimerReferencia.RFC = tb_pr_RFC.Text;
            PrimerReferencia.Direccion = tb_pr_Direccion.Text;
            PrimerReferencia.TelefonoCasa = tb_pr_TelefonoCasa.Text;
            PrimerReferencia.TelefonoCelular = tb_pr_TelefonoCelular.Text;
            PrimerReferencia.CorreoElectronico = tb_pr_CorreoElectronico.Text;
            PrimerReferencia.FechaNacimiento = DateTime.Parse(tb_pr_FechaNacimiento.Text);
            PrimerReferencia.CURP = tb_pr_CURP.Text;
            PrimerReferencia.ClaveElector = tb_pr_ClaveElector.Text;
            if (fu_pr_Foto.HasFile)
            {
                PrimerReferencia.URLFoto = fu_pr_Foto.FileName;
            }
        }

        /// <summary>
        /// FUNCIÓN PARA OBTENER LA SEGUNDA REFERENCIA COMO RESULTADO DE LO INGRESADO EN EL FORMULARIO
        /// </summary>
        /// <returns></returns>
        private void SegundaReferenciaFormulario(ReferenciasPromotores SegundaReferencia)
        {
            SegundaReferencia.Nombre = tb_sr_Nombre.Text;
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
        /// FUNCIÓN PARA VALIDAR LA INFORMACIÓN DEL FORMULARIO
        /// </summary>
        /// <returns></returns>
        private bool ValidarInformacion()
        {
            try
            {
                DateTime fechaNacimiento = DateTime.Parse(tb_FechaNacimiento.Text);
                DateTime pr_fechaNacimiento = DateTime.Parse(tb_pr_FechaNacimiento.Text);
                DateTime sr_fechaNacimiento = DateTime.Parse(tb_sr_FechaNacimiento.Text);
                if (Request.QueryString["id"] == null)
                {
                    if (!fu_Foto.HasFile)
                    {
                        throw new Exception("Por favor, ingrese la foto del promotor.");
                    }
                    /*DOCUMENTOS DEL PROMOTOR*/
                    if (!fu_ActaNacimiento.HasFile)
                    {
                        throw new Exception("Por favor, ingrese el acta de nacimiento del promotor.");
                    }
                    if (!fu_ConstanciaResidencia.HasFile)
                    {
                        throw new Exception("Por favor, ingrese la constancia de residencia del promotor.");
                    }
                    if (!fu_CURP.HasFile)
                    {
                        throw new Exception("Por favor, ingrese el CURP del promotor.");
                    }
                    if (!fu_INE.HasFile)
                    {
                        throw new Exception("Por favor, ingrese el INE del promotor.");
                    }
                    if (!fu_ComprobanteDomicilio.HasFile)
                    {
                        throw new Exception("Por favor, ingrese el comprobante de domicilio del promotor.");
                    }
                    /*/DOCUMENTOS DEL PROMOTOR*/
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