using SQLHelper;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using WebAPI.App_Start;

namespace WebAPI.Models
{
    public class ReferenciasPromotores
    {
        #region Propiedades
        private int? _Id;

        public int? Id
        {
            get { return _Id; }
            set { _Id = value; }
        }

        private int? _IdActor;

        public int? IdActor
        {
            get { return _IdActor; }
            set { _IdActor = value; }
        }

        private int? _IdTipoReferencia;

        public int? IdTipoReferencia
        {
            get { return _IdTipoReferencia; }
            set { _IdTipoReferencia = value; }
        }

        private string _Nombre;

        public string Nombre
        {
            get { return _Nombre; }
            set { _Nombre = value; }
        }

        private string _RFC;

        public string RFC
        {
            get { return _RFC; }
            set { _RFC = value; }
        }

        private string _CURP;

        public string CURP
        {
            get { return _CURP; }
            set { _CURP = value; }
        }

        private DateTime? _FechaNacimiento;

        public DateTime? FechaNacimiento
        {
            get { return _FechaNacimiento; }
            set { _FechaNacimiento = value; }
        }

        private string _ClaveElector;

        public string ClaveElector
        {
            get { return _ClaveElector; }
            set { _ClaveElector = value; }
        }

        private string _Direccion;

        public string Direccion
        {
            get { return _Direccion; }
            set { _Direccion = value; }
        }

        private string _TelefonoCasa;

        public string TelefonoCasa
        {
            get { return _TelefonoCasa; }
            set { _TelefonoCasa = value; }
        }

        private string _TelefonoCelular;

        public string TelefonoCelular
        {
            get { return _TelefonoCelular; }
            set { _TelefonoCelular = value; }
        }

        private string _CorreoElectronico;

        public string CorreoElectronico
        {
            get { return _CorreoElectronico; }
            set { _CorreoElectronico = value; }
        }

        private string _URLFoto;

        public string URLFoto
        {
            get { return _URLFoto; }
            set { _URLFoto = value; }
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
        public ReferenciasPromotores()
        {

        }

        public ReferenciasPromotores(int IdUsuario)
        {
            IdEstatus = 1;
            this.IdUsuario = IdUsuario;
        }
        #endregion

        #region Métodos / Funciones
        /// <summary>
        /// Método para agregar una referencia de préstamo.
        /// </summary>
        public DataSet Agregar()
        {
            DataSet Consulta = EjecutarSP(1);
            Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
            return Consulta;
        }

        /// <summary>
        /// Método para actualizar una referencia de préstamo.
        /// </summary>
        public DataSet Actualizar()
        {
            return EjecutarSP(2);
        }

        /// <summary>
        /// Función para consultar todas las referencias de préstamos activas.
        /// </summary>
        /// <returns>Lista con todas las referencias de préstamos activas.</returns>
        public List<ReferenciasPromotores> ConsultarTodo()
        {
            List<ReferenciasPromotores> Referencias = new List<ReferenciasPromotores>();
            ReferenciasPromotores Referencia = new ReferenciasPromotores();
            using (DataSet Consulta = Referencia.EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    ReferenciasPromotores obj = new ReferenciasPromotores
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        IdActor = int.Parse(Fila["IdActor"].ToString()),
                        IdTipoReferencia = int.Parse(Fila["IdTipoReferencia"].ToString()),
                        Nombre = Fila["Nombre"].ToString(),
                        RFC = Fila["RFC"].ToString(),
                        CURP = Fila["CURP"].ToString(),
                        FechaNacimiento = DateTime.Parse(Fila["RFC"].ToString()),
                        ClaveElector = Fila["ClaveElector"].ToString(),
                        Direccion = Fila["Direccion"].ToString(),
                        TelefonoCasa = Fila["TelefonoCasa"].ToString(),
                        TelefonoCelular = Fila["TelefonoCelular"].ToString(),
                        CorreoElectronico = Fila["CorreoElectronico"].ToString(),
                        URLFoto = Fila["URLFoto"].ToString(),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString())
                    };
                    Referencias.Add(obj);
                }
            }
            return Referencias;
        }

        /// <summary>
        /// Función para consultar una referencia
        /// </summary>
        /// <returns></returns>
        public List<ReferenciasPromotores> Consultar()
        {
            List<ReferenciasPromotores> Referencias = new List<ReferenciasPromotores>();
            ReferenciasPromotores Referencia = new ReferenciasPromotores();
            using (DataSet Consulta = Referencia.EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    ReferenciasPromotores obj = new ReferenciasPromotores
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        IdActor = int.Parse(Fila["IdActor"].ToString()),
                        IdTipoReferencia = int.Parse(Fila["IdTipoReferencia"].ToString()),
                        Nombre = Fila["Nombre"].ToString(),
                        RFC = Fila["RFC"].ToString(),
                        CURP = Fila["CURP"].ToString(),
                        FechaNacimiento = DateTime.Parse(Fila["FechaNacimiento"].ToString()),
                        ClaveElector = Fila["ClaveElector"].ToString(),
                        Direccion = Fila["Direccion"].ToString(),
                        TelefonoCasa = Fila["TelefonoCasa"].ToString(),
                        TelefonoCelular = Fila["TelefonoCelular"].ToString(),
                        CorreoElectronico = Fila["CorreoElectronico"].ToString(),
                        URLFoto = Fila["URLFoto"].ToString(),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString())
                    };
                    Referencias.Add(obj);
                }
            }
            return Referencias;
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
                new SqlParameter("@IdActor", IdActor),
                new SqlParameter("@IdTipoReferencia", IdTipoReferencia),
                new SqlParameter("@Nombre", Nombre),
                new SqlParameter("@RFC", RFC),
                new SqlParameter("@CURP", CURP),
                new SqlParameter("@FechaNacimiento", FechaNacimiento),
                new SqlParameter("@ClaveElector", ClaveElector),
                new SqlParameter("@Direccion", Direccion),
                new SqlParameter("@TelefonoCasa", TelefonoCasa),
                new SqlParameter("@TelefonoCelular", TelefonoCelular),
                new SqlParameter("@CorreoElectronico", CorreoElectronico),
                new SqlParameter("@URLFoto", URLFoto),
                new SqlParameter("@IdEstatus", IdEstatus),
                new SqlParameter("@IdUsuarioActual", IdUsuario)
            };

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[datos].[SPReferenciasPromotores]", Parametros.ToArray());
        }
        #endregion
    }
}