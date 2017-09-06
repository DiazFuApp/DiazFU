using DiazFu.App_Code.Entidades;
using DiazFu.App_Code.Utilerias;
using System;
using System.Data;
using System.Web.UI.WebControls;

namespace DiazFu.Modules.Administracion.Promotores
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
            App_Code.Entidades.Promotores Promotores = new App_Code.Entidades.Promotores();
            gvPromotores.DataSource = Promotores.ConsultarTodo();
            gvPromotores.DataBind();
        }

        /// <summary>
        /// Evento para eliminar el promotor
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void bEliminar_Click(object sender, EventArgs e)
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            Button boton = (Button)sender;
            int id = int.Parse(boton.CommandArgument);
            App_Code.Entidades.Promotores Promotor = new App_Code.Entidades.Promotores
            {
                Id = id,
                IdUsuario = IDUsuarioActual
            };
            Promotor.ConsultarID();
            Promotor.IdEstatus = 2;
            Promotor.Actualizar();
            Literal literal = (Literal)Master.FindControl("lAlerta");
            if (Promotor.Id != 0)
            {
                //BAJA DE REDES SOCIALES DEL PROMOTOR
                RedesSociales RedesPromotor = new RedesSociales
                {
                    IdActor = Promotor.Id,
                    IdTipoActor = 2
                };
                using (DataSet ConsultaRedesPromotor = RedesPromotor.ConsultarTodo())
                {
                    foreach (DataRow FilaRedPromotor in ConsultaRedesPromotor.Tables[0].Rows)
                    {
                        RedesSociales RedSocial = new RedesSociales
                        {
                            Id = int.Parse(FilaRedPromotor["Id"].ToString()),
                            IdTipoActor = 2
                        };
                        RedSocial.ConsultarID();
                        RedSocial.IdEstatus = 2;
                        RedSocial.Actualizar();
                    }
                }
                //BAJA DE DOCUMENTOS DEL PROMOTOR
                Documentos Documentos = new Documentos()
                {
                    IdActor = Promotor.Id,
                    IdTipoActor = 2
                };
                using (DataSet ConsultaDocumentosPromotor = Documentos.ConsultarTodo())
                {
                    foreach (DataRow FilaDocumentoPromotor in ConsultaDocumentosPromotor.Tables[0].Rows)
                    {
                        Documentos Documento = new Documentos()
                        {
                            Id = int.Parse(FilaDocumentoPromotor["Id"].ToString()),
                            IdTipoActor = 2
                        };
                        Documento.ConsultarID();
                        Documento.IdEstatus = 2;
                        Documento.Actualizar();
                    }
                }
                //REFERENCIAS
                ReferenciasPromotores ReferenciasPromotor = new ReferenciasPromotores()
                {
                    IdActor = Promotor.Id
                };
                using (DataSet ConsultaReferencias = ReferenciasPromotor.ConsultarTodo())
                {
                    foreach (DataRow FilaReferencias in ConsultaReferencias.Tables[0].Rows)
                    {
                        ReferenciasPromotores Referencia = new ReferenciasPromotores()
                        {
                            Id = int.Parse(FilaReferencias["Id"].ToString())
                        };
                        Referencia.ConsultarID();
                        Referencia.IdEstatus = 2;
                        Referencia.Actualizar();
                        //BAJA DE REDES SOCIALES DE LA REFERENCIA
                        RedesSociales RedesReferencia = new RedesSociales
                        {
                            IdActor = Referencia.Id,
                            IdTipoActor = 5
                        };
                        using (DataSet ConsultaRedesReferencia = RedesReferencia.ConsultarTodo())
                        {
                            foreach (DataRow FilaRedReferencia in ConsultaRedesReferencia.Tables[0].Rows)
                            {
                                RedesSociales RedSocial = new RedesSociales
                                {
                                    Id = int.Parse(FilaRedReferencia["Id"].ToString()),
                                    IdTipoActor = 5
                                };
                                RedSocial.ConsultarID();
                                RedSocial.IdEstatus = 2;
                                RedSocial.Actualizar();
                            }
                        }
                        //BAJA DE DOCUMENTOS DEL PROMOTOR
                        Documentos DocumentosReferencia = new Documentos()
                        {
                            IdActor = Referencia.Id,
                            IdTipoActor = 5
                        };
                        using (DataSet ConsultaDocumentosReferencia = DocumentosReferencia.ConsultarTodo())
                        {
                            foreach (DataRow FilaDocumentoReferencia in ConsultaDocumentosReferencia.Tables[0].Rows)
                            {
                                Documentos Documento = new Documentos()
                                {
                                    Id = int.Parse(FilaDocumentoReferencia["Id"].ToString()),
                                    IdTipoActor = 5
                                };
                                Documento.ConsultarID();
                                Documento.IdEstatus = 2;
                                Documento.Actualizar();
                            }
                        }
                    }
                }
                literal.Text = Herramientas.Alerta("Operación existosa!", "Promotor eliminado correctamente.", 3);
            }
            else
            {
                literal.Text = Herramientas.Alerta("Ocurrió un error!", "No ha sido posible eliminar al promotor.", 4);
            }
            CargarGrid();
        }
    }
}