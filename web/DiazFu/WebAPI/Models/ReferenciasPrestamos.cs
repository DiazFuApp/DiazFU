using SQLHelper;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using WebAPI.App_Start;

namespace WebAPI.Models
{
    public class ReferenciasPrestamos
    {
        #region Propiedades
        private int? _Id;

        public int? Id
        {
            get { return _Id; }
            set { _Id = value; }
        }

        private int? _IdPrestamo;

        public int? IdPrestamo
        {
            get { return _IdPrestamo; }
            set { _IdPrestamo = value; }
        }

        private int? _IdTipoPrestamo;

        public int? IdTipoPrestamo
        {
            get { return _IdTipoPrestamo; }
            set { _IdTipoPrestamo = value; }
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

        private string _ReferenciaDireccion;

        public string ReferenciaDireccion
        {
            get { return _ReferenciaDireccion; }
            set { _ReferenciaDireccion = value; }
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

        private string _Parentesco;

        public string Parentesco
        {
            get { return _Parentesco; }
            set { _Parentesco = value; }
        }

        private string _URLFoto;

        public string URLFoto
        {
            get { return _URLFoto; }
            set { _URLFoto = value; }
        }

        private string _Empresa;

        public string Empresa
        {
            get { return _Empresa; }
            set { _Empresa = value; }
        }

        private string _PuestoEmpresa;

        public string PuestoEmpresa
        {
            get { return _PuestoEmpresa; }
            set { _PuestoEmpresa = value; }
        }

        private string _DireccionEmpresa;

        public string DireccionEmpresa
        {
            get { return _DireccionEmpresa; }
            set { _DireccionEmpresa = value; }
        }

        private string _AntiguedadEmpresa;

        public string AntiguedadEmpresa
        {
            get { return _AntiguedadEmpresa; }
            set { _AntiguedadEmpresa = value; }
        }

        private string _TelefonoEmpresa;

        public string TelefonoEmpresa
        {
            get { return _TelefonoEmpresa; }
            set { _TelefonoEmpresa = value; }
        }

        private string _NombreJefe;

        public string NombreJefe
        {
            get { return _NombreJefe; }
            set { _NombreJefe = value; }
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
        public ReferenciasPrestamos()
        {

        }

        public ReferenciasPrestamos(int IdUsuario)
        {
            this.IdEstatus = 1;
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
            this.Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
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
        public List<ReferenciasPrestamos> ConsultarTodo()
        {
            List<ReferenciasPrestamos> Referencias = new List<ReferenciasPrestamos>();
            ReferenciasPrestamos Referencia = new ReferenciasPrestamos();
            using (DataSet Consulta = Referencia.EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    ReferenciasPrestamos obj = new ReferenciasPrestamos
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        IdPrestamo = int.Parse(Fila["IdPrestamo"].ToString()),
                        IdTipoPrestamo = int.Parse(Fila["IdTipoPrestamo"].ToString()),
                        IdTipoReferencia = int.Parse(Fila["IdTipoReferencia"].ToString()),
                        Nombre = Fila["Nombre"].ToString(),
                        RFC = Fila["RFC"].ToString(),
                        CURP = Fila["CURP"].ToString(),
                        FechaNacimiento = DateTime.Parse(Fila["RFC"].ToString()),
                        ClaveElector = Fila["ClaveElector"].ToString(),
                        Direccion = Fila["Direccion"].ToString(),
                        ReferenciaDireccion = Fila["ReferenciaDireccion"].ToString(),
                        TelefonoCasa = Fila["TelefonoCasa"].ToString(),
                        TelefonoCelular = Fila["TelefonoCelular"].ToString(),
                        CorreoElectronico = Fila["CorreoElectronico"].ToString(),
                        Parentesco = Fila["Parentesco"].ToString(),
                        URLFoto = Fila["URLFoto"].ToString(),
                        Empresa = Fila["Empresa"].ToString(),
                        PuestoEmpresa = Fila["PuestoEmpresa"].ToString(),
                        DireccionEmpresa = Fila["DireccionEmpresa"].ToString(),
                        AntiguedadEmpresa = Fila["AntiguedadEmpresa"].ToString(),
                        TelefonoEmpresa = Fila["TelefonoEmpresa"].ToString(),
                        NombreJefe = Fila["NombreJefe"].ToString(),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString())
                    };
                    Referencias.Add(obj);
                }
            }
            return Referencias;
        }

        /// <summary>
        /// Método para consultar una referencia de préstamo según su ID.
        /// </summary>
        public List<ReferenciasPrestamos> Consultar()
        {
            List<ReferenciasPrestamos> Referencias = new List<ReferenciasPrestamos>();
            ReferenciasPrestamos Referencia = new ReferenciasPrestamos();
            using (DataSet Consulta = Referencia.EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    ReferenciasPrestamos obj = new ReferenciasPrestamos
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        IdPrestamo = int.Parse(Fila["IdPrestamo"].ToString()),
                        IdTipoPrestamo = int.Parse(Fila["IdTipoPrestamo"].ToString()),
                        IdTipoReferencia = int.Parse(Fila["IdTipoReferencia"].ToString()),
                        Nombre = Fila["Nombre"].ToString(),
                        RFC = Fila["RFC"].ToString(),
                        CURP = Fila["CURP"].ToString(),
                        FechaNacimiento = DateTime.Parse(Fila["RFC"].ToString()),
                        ClaveElector = Fila["ClaveElector"].ToString(),
                        Direccion = Fila["Direccion"].ToString(),
                        ReferenciaDireccion = Fila["ReferenciaDireccion"].ToString(),
                        TelefonoCasa = Fila["TelefonoCasa"].ToString(),
                        TelefonoCelular = Fila["TelefonoCelular"].ToString(),
                        CorreoElectronico = Fila["CorreoElectronico"].ToString(),
                        Parentesco = Fila["Parentesco"].ToString(),
                        URLFoto = Fila["URLFoto"].ToString(),
                        Empresa = Fila["Empresa"].ToString(),
                        PuestoEmpresa = Fila["PuestoEmpresa"].ToString(),
                        DireccionEmpresa = Fila["DireccionEmpresa"].ToString(),
                        AntiguedadEmpresa = Fila["AntiguedadEmpresa"].ToString(),
                        TelefonoEmpresa = Fila["TelefonoEmpresa"].ToString(),
                        NombreJefe = Fila["NombreJefe"].ToString(),
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
                new SqlParameter("@IdPrestamo", IdPrestamo),
                new SqlParameter("@IdTipoPrestamo", IdTipoPrestamo),
                new SqlParameter("@IdTipoReferencia", IdTipoReferencia),
                new SqlParameter("@Nombre", Nombre),
                new SqlParameter("@RFC", RFC),
                new SqlParameter("@CURP", CURP),
                new SqlParameter("@FechaNacimiento", FechaNacimiento),
                new SqlParameter("@ClaveElector", ClaveElector),
                new SqlParameter("@Direccion", Direccion),
                new SqlParameter("@ReferenciaDireccion", ReferenciaDireccion),
                new SqlParameter("@TelefonoCasa", TelefonoCasa),
                new SqlParameter("@TelefonoCelular", TelefonoCelular),
                new SqlParameter("@CorreoElectronico", CorreoElectronico),
                new SqlParameter("@Parentesco", Parentesco),
                new SqlParameter("@URLFoto", URLFoto),
                new SqlParameter("@Empresa", Empresa),
                new SqlParameter("@PuestoEmpresa", PuestoEmpresa),
                new SqlParameter("@DireccionEmpresa", DireccionEmpresa),
                new SqlParameter("@AntiguedadEmpresa", AntiguedadEmpresa),
                new SqlParameter("@TelefonoEmpresa", TelefonoEmpresa),
                new SqlParameter("@NombreJefe", NombreJefe),
                new SqlParameter("@IdEstatus", IdEstatus),
                new SqlParameter("@IdUsuarioActual", IdUsuario)
            };

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[datos].[SPReferenciasPrestamos]", Parametros.ToArray());
        }
        #endregion
    }
}