using DiazFu.App_Code.Utilerias;
using SQLHelper;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;

namespace DiazFu.App_Code.Entidades
{
    public class Pagos
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

        private int? _IdCliente;

        public int? IdCliente
        {
            get { return _IdCliente; }
            set { _IdCliente = value; }
        }

        private int? _IdTipoPrestamo;

        public int? IdTipoPrestamo
        {
            get { return _IdTipoPrestamo; }
            set { _IdTipoPrestamo = value; }
        }

        private float _MontoAPagar;

        public float MontoAPagar
        {
            get { return _MontoAPagar; }
            set { _MontoAPagar = value; }
        }

        private float _MontoPagado;

        public float MontoPagado
        {
            get { return _MontoPagado; }
            set { _MontoPagado = value; }
        }

        private string _Plazo;

        public string Plazo
        {
            get { return _Plazo; }
            set { _Plazo = value; }
        }

        private string _TipoPago;

        public string TipoPago
        {
            get { return _TipoPago; }
            set { _TipoPago = value; }
        }

        private DateTime _FechaProgramada;

        public DateTime FechaProgramada
        {
            get { return _FechaProgramada; }
            set { _FechaProgramada = value; }
        }
        private DateTime? _FechaPago;

        public DateTime? FechaPago
        {
            get { return _FechaPago; }
            set { _FechaPago = value; }
        }

        private float _Morosidad;

        public float Morosidad
        {
            get { return _Morosidad; }
            set { _Morosidad = value; }
        }

        private string _Descripcion;

        public string Descripcion
        {
            get { return _Descripcion; }
            set { _Descripcion = value; }
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
        public Pagos()
        {
            FechaProgramada = DateTime.Now;
        }

        public Pagos(int IdUsuario)
        {
            IdEstatus = 1;
            FechaProgramada = DateTime.Now;
            this.IdUsuario = IdUsuario;
        }
        #endregion

        #region Métodos / Funciones
        /// <summary>
        /// Método para agregar un pago.
        /// </summary>
        public DataSet Agregar()
        {
            DataSet Consulta = EjecutarSP(1);
            Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
            return Consulta;
        }

        /// <summary>
        /// Método para actualizar un pago.
        /// </summary>
        public DataSet Actualizar()
        {
            return EjecutarSP(2);
        }

        /// <summary>
        /// Función para consultar todos los pagos activos.
        /// </summary>
        /// <returns>Data Set con todos los pagos activos.</returns>
        public DataSet ConsultarTodo()
        {
            return EjecutarSP(3);
        }

        /// <summary>
        /// Método para consultar un pago según su ID.
        /// </summary>
        public void ConsultarID()
        {
            DataSet Consulta = new DataSet();
            Consulta = EjecutarSP(3);
            if (Consulta.Tables[0].Rows.Count > 0)
            {
                DataRow Fila = Consulta.Tables[0].Rows[0];
                Id = int.Parse(Fila["Id"].ToString());
                IdPrestamo = int.Parse(Fila["IdPrestamo"].ToString());
                IdCliente = int.Parse(Fila["IdCliente"].ToString());
                IdTipoPrestamo = int.Parse(Fila["IdTipoPrestamo"].ToString());
                MontoAPagar = float.Parse(Fila["MontoAPagar"].ToString());
                MontoPagado = float.Parse(Fila["MontoPagado"].ToString());
                Plazo = Fila["Plazo"].ToString();
                TipoPago = Fila["TipoPago"].ToString();
                if (Fila["FechaProgramada"].ToString() != string.Empty)
                {
                    FechaProgramada = DateTime.Parse(Fila["FechaProgramada"].ToString());
                }
                if (Fila["FechaPago"].ToString() != string.Empty)
                {
                    FechaPago = DateTime.Parse(Fila["FechaPago"].ToString());
                }
                Morosidad = float.Parse(Fila["Morosidad"].ToString());
                Descripcion = Fila["Descripcion"].ToString();
                IdEstatus = int.Parse(Fila["IdEstatus"].ToString());
            }
            else
            {
                Id = null;
            }
        }

        /// <summary>
        /// Función para consultar los próximos pagos.
        /// </summary>
        /// <returns>Data Set con todos los pagos activos.</returns>
        public DataSet ConsultarProximosPagos()
        {
            return EjecutarSP(4);
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
                new SqlParameter("@IdCliente", IdCliente),
                new SqlParameter("@IdTipoPrestamo", IdTipoPrestamo),
                new SqlParameter("@MontoAPagar", MontoAPagar),
                new SqlParameter("@MontoPagado", MontoPagado),
                new SqlParameter("@Plazo", Plazo),
                new SqlParameter("@TipoPago", TipoPago),
                new SqlParameter("@FechaProgramada", FechaProgramada),
                new SqlParameter("@FechaPago", FechaPago),
                new SqlParameter("@Morosidad", Morosidad),
                new SqlParameter("@Descripcion", Descripcion),
                new SqlParameter("@IdEstatus", IdEstatus),
                new SqlParameter("@IdUsuarioActual", IdUsuario)
            };

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[datos].[SPPagos]", Parametros.ToArray());
        }
        #endregion
    }
}