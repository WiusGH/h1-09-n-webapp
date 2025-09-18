import React, { useState } from "react";
import style from "./ProfileEditor.module.css";
import { getUserData } from "../../utils/userStorage";
import type { UserData, Project } from "../../types/Types";
import GenericButton from "../buttons/GenericButton";
import SkillTag from "../buttons/SkillTag";
import EditCv from "./EditCv";

interface ProfileEditorProps {
  section: string;
  onCancel: () => void;
  onSave: (updatedUser: UserData) => void;
}

/**
 * Componente para editar la información de un usuario
 * Recibe una sección que indica qué parte de la información se debe editar
 * Recibe una función onCancel que se ejecuta al cancelar la edición
 * Recibe una función onSave que se ejecuta al guardar los cambios
 * @param section - La sección que se debe editar (image, personal, skills, portfolio, cv, contact)
 * @param onCancel - La función que se ejecuta al cancelar la edición
 * @param onSave - La función que se ejecuta al guardar los cambios
 */
const ProfileEditor: React.FC<ProfileEditorProps> = ({
  section,
  onCancel,
  onSave,
}) => {
  const initialUser = getUserData();
  const [user, setUser] = useState<UserData | null>(initialUser);
  const [newSkill, setNewSkill] = useState<string>("");
  const [newProject, setNewProject] = useState<Project>({ title: "", url: "" });

  if (!user) {
    return (
      <div className={style.container}>
        <h3>No se encontraron datos de usuario.</h3>
      </div>
    );
  }

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    const { name, value } = e.target;
    setUser((prevUser) => ({
      ...prevUser!,
      [name]: value,
    }));
  };

  const handleAddSkill = () => {
    if (newSkill.trim() === "") return;
    setUser((prevUser) => ({
      ...prevUser!,
      skills: [...(prevUser?.skills || []), newSkill.trim()],
    }));
    setNewSkill("");
  };

  const handleRemoveSkill = (skillToRemove: string) => {
    setUser((prevUser) => ({
      ...prevUser!,
      skills: (prevUser?.skills || []).filter(
        (skill) => skill !== skillToRemove
      ),
    }));
  };

  const handleAddProject = () => {
    if (newProject.title.trim() === "" || newProject.url.trim() === "") return;
    setUser((prevUser) => ({
      ...prevUser!,
      portfolio: [...(prevUser?.portfolio || []), newProject],
    }));
    setNewProject({ title: "", url: "" });
  };

  const handleNewProjectChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setNewProject((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSave(user);
  };

  return (
    <div className={style.container}>
      <form onSubmit={handleSubmit}>
        {/* Nueva sección: Editar Foto de Perfil */}
        {section === "image" && (
          <div className={style.section}>
            <h2>Editar Foto de Perfil</h2>
            <div className={style.formGroup}>
              <label htmlFor="image">URL de la Imagen de Perfil:</label>
              <input
                type="text"
                id="image"
                name="image"
                value={user.image || ""}
                onChange={handleChange}
                className={style.input}
              />
            </div>
          </div>
        )}

        {section === "personal" && (
          <div className={style.section}>
            <h2>Editar Información Personal</h2>
            <div className={style.formGroup}>
              <label htmlFor="firstName">Nombre:</label>
              <input
                type="text"
                id="firstName"
                name="firstName"
                value={user.name}
                onChange={handleChange}
                className={style.input}
              />
            </div>
            <div className={style.formGroup}>
              <label htmlFor="lastName">Apellido:</label>
              <input
                type="text"
                id="lastName"
                name="lastName"
                value={user.lastName}
                onChange={handleChange}
                className={style.input}
              />
            </div>
            <div className={style.formGroup}>
              <label htmlFor="title">Cargo/Ocupación:</label>
              <input
                type="text"
                id="title"
                name="title"
                value={user.title || ""}
                onChange={handleChange}
                className={style.input}
              />
            </div>
            <div className={style.formGroup}>
              <label htmlFor="biography">Biografía:</label>
              <textarea
                id="biography"
                name="biography"
                value={user.biography || ""}
                onChange={handleChange}
                className={style.textarea}
              />
            </div>
          </div>
        )}

        {/* Secciones de Skills y Portafolio sin cambios */}
        {section === "skills" && (
          <div className={style.section}>
            <h2>Editar Skills</h2>
            <div className={style.skillsContainer}>
              {(user.skills || []).map((skill, idx) => (
                <SkillTag
                  key={idx}
                  skill={skill}
                  onRemove={() => handleRemoveSkill(skill)}
                />
              ))}
            </div>
            <div className={style.addSkillContainer}>
              <input
                type="text"
                placeholder="Añadir nueva skill..."
                value={newSkill}
                onChange={(e) => setNewSkill(e.target.value)}
                className={style.input}
              />
              <GenericButton text="Añadir" onClick={handleAddSkill} />
            </div>
          </div>
        )}

        {section === "portfolio" && (
          <div className={style.section}>
            <h2>Editar Portafolio</h2>
            <div className={style.portfolioProjects}>
              {(user.portfolio || []).map((project, idx) => (
                <div key={idx} className={style.projectItem}>
                  <span>{project.title}</span>
                  <a
                    href={project.url}
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    Ver
                  </a>
                </div>
              ))}
            </div>
            <div className={style.addProjectContainer}>
              <input
                type="text"
                name="title"
                placeholder="Título del proyecto"
                value={newProject.title}
                onChange={handleNewProjectChange}
                className={style.input}
              />
              <input
                type="url"
                name="url"
                placeholder="URL de GitHub"
                value={newProject.url}
                onChange={handleNewProjectChange}
                className={style.input}
              />
              <GenericButton
                text="Añadir Proyecto"
                onClick={handleAddProject}
              />
            </div>
          </div>
        )}

        {/* Sección de CV - Ahora es un componente modular */}
        {section === "cv" && <EditCv user={user} handleChange={handleChange} />}

        {/* Sección para editar el Contacto */}
        {section === "contact" && (
          <div className={style.section}>
            <h2>Editar Contacto</h2>
            <div className={style.formGroup}>
              <label htmlFor="email">Correo Electrónico:</label>
              <input
                type="email"
                id="email"
                name="email"
                value={user.email}
                onChange={handleChange}
                className={style.input}
              />
            </div>
            <div className={style.formGroup}>
              <label htmlFor="phoneNumber">Teléfono:</label>
              <input
                type="text"
                id="phoneNumber"
                name="phoneNumber"
                value={user.phoneNumber || ""}
                onChange={handleChange}
                className={style.input}
              />
            </div>
            <div className={style.formGroup}>
              <label htmlFor="country">País:</label>
              <input
                type="text"
                id="country"
                name="country"
                value={user.country || ""}
                onChange={handleChange}
                className={style.input}
              />
            </div>
            <div className={style.formGroup}>
              <label htmlFor="address">Dirección:</label>
              <input
                type="text"
                id="address"
                name="address"
                value={user.address || ""}
                onChange={handleChange}
                className={style.input}
              />
            </div>
          </div>
        )}

        <div className={style.actions}>
          <button
            type="button"
            onClick={onCancel}
            className={style.cancelButton}
          >
            Cancelar
          </button>
          <button type="submit" className={style.saveButton}>
            Guardar Cambios
          </button>
        </div>
      </form>
    </div>
  );
};

export default ProfileEditor;
