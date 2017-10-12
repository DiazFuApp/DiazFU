﻿using SQLHelper;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using WebAPI.App_Start;

namespace WebAPI.Models
{
    public class Actividades
    {
        #region Propiedades
        private int? _Id;

        public int? Id
        {
            get { return _Id; }
            set { _Id = value; }
        }

        private int? _IdPromotor;

        public int? IdPromotor
        {
            get { return _IdPromotor; }
            set { _IdPromotor = value; }
        }

        private string _Titulo;

        public string Titulo
        {
            get { return _Titulo; }
            set { _Titulo = value; }
        }

        private string _Descripcion;

        public string Descripcion
        {
            get { return _Descripcion; }
            set { _Descripcion = value; }
        }

        private int? _IdPrioridad;

        public int? IdPrioridad
        {
            get { return _IdPrioridad; }
            set { _IdPrioridad = value; }
        }

        private int? _IdEstatus;

        public int? IdEstatus
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
        public Actividades()
        {

        }

        public Actividades(int IdUsuario)
        {
            IdEstatus = 1;
            this.IdUsuario = IdUsuario;
        }
        #endregion

        #region Métodos / Funciones
        /// <summary>
        /// Método para agregar una actividad.
        /// </summary>
        public DataSet Agregar()
        {
            DataSet Consulta = EjecutarSP(1);
            Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
            return Consulta;
        }

        /// <summary>
        /// Método para actualizar una actividad.
        /// </summary>
        public DataSet Actualizar()
        {
            return EjecutarSP(2);
        }

        /// <summary>
        /// Función para consultar todas las actividades activas.
        /// </summary>
        /// <returns>Lista con todas las actividades activas.</returns>
        public List<Actividades> ConsultarTodo()
        {
            List<Actividades> Actividades = new List<Actividades>();
            Actividades Actividad = new Actividades();
            using (DataSet Consulta = Actividad.EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    Actividades obj = new Actividades
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        IdPromotor = int.Parse(Fila["IdPromotor"].ToString()),
                        Titulo = Fila["Titulo"].ToString(),
                        Descripcion = Fila["Descripcion"].ToString(),
                        IdPrioridad = int.Parse(Fila["IdPrioridad"].ToString()),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString()),
                    };
                    Actividades.Add(obj);
                }
            }
            return Actividades;
        }

        /// <summary>
        /// Método para consultar una actividad según su ID.
        /// </summary>
        /// <returns>Lista con todas las actividades activas.</returns>
        public List<Actividades> Consultar()
        {
            List<Actividades> Actividades = new List<Actividades>();
            Actividades Actividad = new Actividades();
            using (DataSet Consulta = Actividad.EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    Actividades obj = new Actividades
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        IdPromotor = int.Parse(Fila["IdPromotor"].ToString()),
                        Titulo = Fila["Titulo"].ToString(),
                        Descripcion = Fila["Descripcion"].ToString(),
                        IdPrioridad = int.Parse(Fila["IdPrioridad"].ToString()),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString()),
                    };
                    Actividades.Add(obj);
                }
            }
            return Actividades;
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
                new SqlParameter("@IdPromotor", IdPromotor),
                new SqlParameter("@Titulo", Titulo),
                new SqlParameter("@Descripcion", Descripcion),
                new SqlParameter("@IdPrioridad", IdPrioridad),
                new SqlParameter("@IdEstatus", IdEstatus),
                new SqlParameter("@IdUsuarioActual", IdUsuario)
            };

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[datos].[SPActividades]", Parametros.ToArray());
        }
        #endregion
    }
}