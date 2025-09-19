import { useEffect, useState } from "react";
import { pendingRecruiterRequests } from "../../api/admin-apis/pendingRecruiterRequests";
import style from "./ControlPanelView.module.css";
import GenericButton from "../buttons/GenericButton";

interface RecruiterRequest {
  userId: number;
  companyName: string;
  companyCountry: string;
  companyAddress: string;
  companyEmail: string;
  approved: boolean;
}

/**
 * Componente que muestra un panel de control para los administradores
 * donde se pueden ver las solicitudes pendientes de reclutador y aprobarlas
 */
const ControlPanelView = () => {
  const [requests, setRequests] = useState<RecruiterRequest[]>([]);
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    async function fetchRequests() {
      setLoading(true);
      setErrorMessage("");
      try {
        const data = await pendingRecruiterRequests();
        setRequests(data);
      } catch {
        setErrorMessage("Error al cargar las solicitudes pendientes");
      } finally {
        setLoading(false);
      }
    }
    fetchRequests();
  }, []);

  function handleApprove(userId: number) {
    // 🚧 replace with approve API later
    console.log("Approving user:", userId);
  }

  return (
    <section
      className={style.container}
      role="main"
      aria-label="Panel de control"
    >
      <h2 className={style.title}>Solicitudes pendientes de reclutador</h2>

      {loading && <p className={style.loading}>Cargando...</p>}
      {errorMessage && <p className={style.error}>{errorMessage}</p>}
      {!loading && requests.length === 0 && (
        <p className={style.empty}>No hay solicitudes pendientes.</p>
      )}

      <ul className={style.list}>
        {requests.map((req) => (
          <li key={req.userId} className={style.item}>
            <h3>{req.companyName}</h3>
            <p>
              <strong>País:</strong> {req.companyCountry}
            </p>
            <p>
              <strong>Dirección:</strong> {req.companyAddress}
            </p>
            <p>
              <strong>Email:</strong> {req.companyEmail}
            </p>
            <p>
              <strong>Aprobado:</strong> {req.approved ? "Sí" : "No"}
            </p>
            <GenericButton
              text="Aprobar"
              onClick={() => handleApprove(req.userId)}
            />
          </li>
        ))}
      </ul>
    </section>
  );
};

export default ControlPanelView;
