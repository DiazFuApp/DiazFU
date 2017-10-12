using SQLHelper;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using WebAPI.App_Start;

namespace WebAPI.Models
{
    public class Documentos
    {
        #region Propiedades
        private int? _Id;

        public int? Id
        {
            get { return _Id; }
            set { _Id = value; }
        }

        private int? _IdTipoDocumento;

        public int? IdTipoDocumento
        {
            get { return _IdTipoDocumento; }
            set { _IdTipoDocumento = value; }
        }

        private int? _IdTipoActor;

        public int? IdTipoActor
        {
            get { return _IdTipoActor; }
            set { _IdTipoActor = value; }
        }

        private int? _IdActor;

        public int? IdActor
        {
            get { return _IdActor; }
            set { _IdActor = value; }
        }

        private string _URLDocumento;

        public string URLDocumento
        {
            get { return _URLDocumento; }
            set { _URLDocumento = value; }
        }

        private int _IdEstatus;

        public int IdEstatus
        {
            get { return _IdEstatus; }
            set { _IdEstatus = value; }
        }

        private int _IdUsuario;

        public int IdUsuario
        {
            get { return _IdUsuario; }
            set { _IdUsuario = value; }
        }
        #endregion

        #region Constructor
        public Documentos()
        {

        }

        public Documentos(int IdUsuario)
        {
            IdEstatus = 1;
            this.IdUsuario = IdUsuario;
        }
        #endregion

        #region Métodos / Funciones
        /// <summary>
        /// Método para agregar un documento.
        /// </summary>
        public DataSet Agregar()
        {
            DataSet Consulta = EjecutarSP(1);
            Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
            return Consulta;
        }

        /// <summary>
        /// Método para actualizar un documento.
        /// </summary>
        public DataSet Actualizar()
        {
            return EjecutarSP(2);
        }

        /// <summary>
        /// Función para consultar todos los documentos activos.
        /// </summary>
        /// <returns>Lista con todos los promotores activos.</returns>
        public List<Documentos> ConsultarTodo()
        {
            List<Documentos> Documentos = new List<Documentos>();
            Documentos Documento = new Documentos();
            using (DataSet Consulta = Documento.EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    Documentos obj = new Documentos
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        IdTipoDocumento = int.Parse(Fila["IdTipoDocumento"].ToString()),
                        IdTipoActor = int.Parse(Fila["IdTipoActor"].ToString()),
                        IdActor = int.Parse(Fila["IdActor"].ToString()),
                        URLDocumento = Fila["URLDocumento"].ToString(),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString())
                    };
                    Documentos.Add(obj);
                }
            }
            return Documentos;
        }

        /// <summary>
        /// Método para consultar un documento según su ID.
        /// </summary>
        public List<Documentos> Consultar()
        {
            List<Documentos> Documentos = new List<Documentos>();
            Documentos Documento = new Documentos();
            using (DataSet Consulta = Documento.EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    Documentos obj = new Documentos
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        IdTipoDocumento = int.Parse(Fila["IdTipoDocumento"].ToString()),
                        IdTipoActor = int.Parse(Fila["IdTipoActor"].ToString()),
                        IdActor = int.Parse(Fila["IdActor"].ToString()),
                        URLDocumento = Fila["URLDocumento"].ToString(),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString())
                    };
                    Documentos.Add(obj);
                }
            }
            return Documentos;
        }

        /// <summary>
        /// Función para ejecutar el procedimiento almacenado seleccionado.
        /// </summary>
        /// <returns>Data Set con la consulta emitida por SQL</returns>
        public DataSet EjecutarSP(int Opcion)
        {
            List<SqlParameter> Parametros = new List<SqlParameter>
            {
                new SqlParameter("@Opcion", Opcion),
                new SqlParameter("@Id", Id),
                new SqlParameter("@IdTipoDocumento", IdTipoDocumento),
                new SqlParameter("@IdActor", IdActor),
                new SqlParameter("@IdTipoActor", IdTipoActor),
                new SqlParameter("@URLDocumento", URLDocumento),
                new SqlParameter("@IdEstatus", IdEstatus),
                new SqlParameter("@IdUsuarioActual", IdUsuario)
            };

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[datos].[SPDocumentos]", Parametros.ToArray());
        }
        #endregion
    }
}