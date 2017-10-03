using DiazFu.App_Code.Utilerias;
using SQLHelper;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;

namespace DiazFu.App_Code.Entidades
{
    public class PrestamosIndividuales
    {
        #region Propiedades
        private int? _Id;

        public int? Id
        {
            get { return _Id; }
            set { _Id = value; }
        }

        private int? _IdCliente;

        public int? IdCliente
        {
            get { return _IdCliente; }
            set { _IdCliente = value; }
        }

        private string _Motivo;

        public string Motivo
        {
            get { return _Motivo; }
            set { _Motivo = value; }
        }

        private float _CantidadSolicitada;

        public float CantidadSolicitada
        {
            get { return _CantidadSolicitada; }
            set { _CantidadSolicitada = value; }
        }

        private float _CantidadOtorgada;

        public float CantidadOtorgada
        {
            get { return _CantidadOtorgada; }
            set { _CantidadOtorgada = value; }
        }

        private float _Interes;

        public float Interes
        {
            get { return _Interes; }
            set { _Interes = value; }
        }

        private DateTime? _FechaEntrega;

        public DateTime? FechaEntrega
        {
            get { return _FechaEntrega; }
            set { _FechaEntrega = value; }
        }

        private string _Observaciones;

        public string Observaciones
        {
            get { return _Observaciones; }
            set { _Observaciones = value; }
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
        public PrestamosIndividuales()
        {

        }

        public PrestamosIndividuales(int IdUsuario)
        {
            IdEstatus = 1;
            this.IdUsuario = IdUsuario;
        }
        #endregion

        #region Métodos / Funciones
        /// <summary>
        /// Método para agregar un préstamo individual.
        /// </summary>
        public DataSet Agregar()
        {
            DataSet Consulta = EjecutarSP(1);
            Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
            return Consulta;
        }

        /// <summary>
        /// Método para actualizar un préstamo individual..
        /// </summary>
        public DataSet Actualizar()
        {
            return EjecutarSP(2);
        }

        /// <summary>
        /// Función para consultar todos los préstamos individuales activos.
        /// </summary>
        /// <returns>Data Set con todos los clientes activos.</returns>
        public DataSet ConsultarTodo()
        {
            Id = null;
            return EjecutarSP(3);
        }

        /// <summary>
        /// Método para consultar un préstamo individual según su ID.
        /// </summary>
        public void ConsultarID()
        {
            DataSet Consulta = new DataSet();
            Consulta = EjecutarSP(3);
            if (Consulta.Tables[0].Rows.Count > 0)
            {
                DataRow Fila = Consulta.Tables[0].Rows[0];
                Id = int.Parse(Fila["Id"].ToString());
                IdCliente = int.Parse(Fila["IdCliente"].ToString());
                Motivo = Fila["Motivo"].ToString();
                CantidadSolicitada = float.Parse(Fila["CantidadSolicitada"].ToString());
                CantidadOtorgada = float.Parse(Fila["CantidadOtorgada"].ToString());
                Interes = float.Parse(Fila["Interes"].ToString());
                FechaEntrega = DateTime.Parse(Fila["FechaEntrega"].ToString());
                Observaciones = Fila["Observaciones"].ToString();
                IdEstatus = int.Parse(Fila["IdEstatus"].ToString());
            }
            else
            {
                Id = null;
            }
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
                new SqlParameter("@IdCliente", IdCliente),
                new SqlParameter("@Motivo", Motivo),
                new SqlParameter("@CantidadSolicitada", CantidadSolicitada),
                new SqlParameter("@CantidadOtorgada", CantidadOtorgada),
                new SqlParameter("@Interes", Interes),
                new SqlParameter("@FechaEntrega", FechaEntrega),
                new SqlParameter("@Observaciones", Observaciones),
                new SqlParameter("@IdEstatus", IdEstatus),
                new SqlParameter("@IdUsuarioActual", IdUsuario)
            };

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[datos].[SPPrestamosIndividuales]", Parametros.ToArray());
        }
        #endregion
    }
}