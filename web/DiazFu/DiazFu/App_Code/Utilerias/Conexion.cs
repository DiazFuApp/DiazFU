using System.Configuration;

namespace DiazFu.App_Code.Utilerias
{
    public class Conexion
    {
        public static string CadenaConexion()
        {
            return ConfigurationManager.ConnectionStrings["DIAZFU_DB"].ConnectionString;
        }
    }
}