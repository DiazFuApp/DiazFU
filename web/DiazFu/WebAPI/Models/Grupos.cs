using SQLHelper;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using WebAPI.App_Start;

namespace WebAPI.Models
{
    public class Grupos
    {
        #region Propiedades
        private int? _Id;

        public int? Id
        {
            get { return _Id; }
            set { _Id = value; }
        }

        private string _Nombre;

        public string Nombre
        {
            get { return _Nombre; }
            set { _Nombre = value; }
        }

        private int? _IdClienteResponsable;

        public int? IdClienteResponsable
        {
            get { return _IdClienteResponsable; }
            set { _IdClienteResponsable = value; }
        }

        private int? _IdPromotor;

        public int? IdPromotor
        {
            get { return _IdPromotor; }
            set { _IdPromotor = value; }
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
        public Grupos()
        {

        }

        public Grupos(int IdUsuario)
        {
            IdEstatus = 1;
            this.IdUsuario = IdUsuario;
        }
        #endregion

        #region Métodos / Funciones
        /// <summary>
        /// Método para agregar un grupo.
        /// </summary>
        public DataSet Agregar()
        {
            DataSet Consulta = EjecutarSP(1);
            Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
            return Consulta;
        }

        /// <summary>
        /// Método para actualizar un grupo.
        /// </summary>
        public DataSet Actualizar()
        {
            return EjecutarSP(2);
        }

        /// <summary>
        /// Función para consultar todos los grupos activos.
        /// </summary>
        /// <returns>Lista con todos los clientes activos.</returns>
        public List<Grupos> ConsultarTodo()
        {
            List<Grupos> Grupos = new List<Grupos>();
            Grupos Grupo = new Grupos();
            using (DataSet Consulta = Grupo.EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    Grupos obj = new Grupos
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        Nombre = Fila["Nombre"].ToString(),
                        IdClienteResponsable = int.Parse(Fila["IdClienteResponsable"].ToString()),
                        IdPromotor = int.Parse(Fila["IdPromotor"].ToString()),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString())
                    };
                    Grupos.Add(obj);
                }
            }
            return Grupos;
        }

        /// <summary>
        /// Método para consultar un grupo.
        /// </summary>
        /// <returns>Lista con todos los grupos activos.</returns>
        public List<Grupos> Consultar()
        {
            List<Grupos> Grupos = new List<Grupos>();
            Grupos Grupo = new Grupos();
            using (DataSet Consulta = Grupo.EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    Grupos obj = new Grupos
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        Nombre = Fila["Nombre"].ToString(),
                        IdClienteResponsable = int.Parse(Fila["IdClienteResponsable"].ToString()),
                        IdPromotor = int.Parse(Fila["IdPromotor"].ToString()),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString())
                    };
                    Grupos.Add(obj);
                }
            }
            return Grupos;
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
                new SqlParameter("@Nombre", Nombre),
                new SqlParameter("@IdClienteResponsable", IdClienteResponsable),
                new SqlParameter("@IdPromotor", IdPromotor),
                new SqlParameter("@IdEstatus", IdEstatus),
                new SqlParameter("@IdUsuarioActual", IdUsuario)
            };

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[datos].[SPGrupos]", Parametros.ToArray());
        }
        #endregion
    }
}