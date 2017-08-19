using System.Collections.Generic;
using System.Data;
using System.Web.Http;
using WebAPI.Models;

namespace WebAPI.Controllers
{
    public class DocumentosController : ApiController
    {
        Documentos Documento = new Documentos();

        /// <summary>
        /// FUNCIÓN PARA OBTENER UN EJEMPLO DEL OBJETO
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/documentos/ejemplo")]
        public Documentos Example()
        {
            return Documento;
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR TODOS LOS DOCUMENTOS
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/documentos")]
        public List<Documentos> GetAll()
        {
            return Documento.ConsultarTodo();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR EL DOCUMENTO POR EL ID
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/documentos/{id}")]
        public List<Documentos> Get(int id)
        {
            Documento.Id = id;
            return Documento.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR EL DOCUMENTO 
        /// </summary>
        /// <param name="Documento"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/documentos/objeto")]
        public List<Documentos> Get([FromBody]Documentos Documento)
        {
            return Documento.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA AGREGAR UN NUEVO DOCUMENTO
        /// </summary>
        /// <param name="Documento"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/documentos/agregar")]
        public DataSet Add([FromBody]Documentos Documento)
        {
            return Documento.Agregar();
        }

        /// <summary>
        /// FUNCIÓN PARA ACTUALIZAR UN DOCUMENTO
        /// </summary>
        /// <param name="Documento"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/documentos/actualizar")]
        public DataSet Update([FromBody]Documentos Documento)
        {
            return Documento.Actualizar();
        }

        /// <summary>
        /// FUNCIÓN PARA DAR DE BAJA A UN DOCUMENTO
        /// </summary>
        /// <param name="Documento"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/documentos/eliminar")]
        public DataSet Delete([FromBody]Documentos Documento)
        {
            Documento.IdEstatus = 2;
            return Documento.Actualizar();
        }
    }
}
