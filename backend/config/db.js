import dotenv from "dotenv";
import sql from "msnodesqlv8";

dotenv.config();

class Database {
  constructor() {
    if (Database.instance) {
      return Database.instance; // Retorna la instancia existente si ya fue creada
    }

    // Define la cadena de conexión UNA SOLA VEZ
    this.connectionString = `Driver=${process.env.DB_DRIVER};Server=${process.env.DB_SERVER};Database=${process.env.DB_NAME};Trusted_Connection=${process.env.DB_TRUSTED_CONNECTION};`;

    Database.instance = this; // Guarda la instancia para futuras referencias
  }

  // Método para ejecutar consultas
  query(query, params = []) {
    return new Promise((resolve, reject) => {
      sql.query(this.connectionString, query, params, (err, rows) => {
        if (err) {
          console.error("❌ Error en la consulta:", err);
          reject(err);
        } else {
          resolve(rows);
        }
      });
    });
  }

  // Método para verificar la conexión inicial
  connect() {
    sql.query(this.connectionString, "SELECT 1", (err) => {
      if (err) {
        console.error("❌ Error al conectar a la base de datos:", err);
      } else {
        console.log("✅ Conexión a SQL Server exitosa");
      }
    });
  }
}

// Exporta una única instancia de la clase Database
const dbInstance = new Database();
export default dbInstance;

// Exporta la función connect para verificar la conexión inicial
export const connectDB = () => {
  dbInstance.connect();
};


