import React from "react";
import styles from "./Modal.module.css";

interface ModalProps {
  isOpen: boolean;
  onClose: () => void;
  children: React.ReactNode;
}

/**
 * Un componente tipo modal que se utiliza para mostrar información adicional en una ventana emergente.
 * El componente acepta tres propiedades: isOpen, onClose y children.
 * La propiedad isOpen indica si el modal esta abierto.
 * La propiedad isClosed indica si el modal esta cerrado.
 * Este modal ocupará el 100% de la pantalla y se mostrara en el centro de la pantalla.
 */
const ModalCard: React.FC<ModalProps> = ({ isOpen, onClose, children }) => {
  if (!isOpen) return null;

  return (
    <div className={styles.overlay} onClick={onClose}>
      <div className={styles.modal} onClick={(e) => e.stopPropagation()}>
        <button className={styles.closeBtn} onClick={onClose}>
          ✖
        </button>
        {children}
      </div>
    </div>
  );
};

export default ModalCard;
