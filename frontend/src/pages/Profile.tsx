import { useState } from "react";
import DynamicContainer from "../components/containers/DynamicContainer";
import ProfileView from "../components/views/ProfileView";
import ProfileEditor from "../components/views/ProfileEditor";
import { getUserData, saveUserData } from "../utils/userStorage";
import type { UserData } from "../types/Types";

const Profile = () => {
  const [editingSection, setEditingSection] = useState<string | null>(null);
  const [user, setUser] = useState<UserData | null>(getUserData());

  const handleEditClick = (section: string) => {
    setEditingSection(section);
  };

  const handleSave = (updatedUser: UserData) => {
    saveUserData(updatedUser);
    setUser(updatedUser);
    setEditingSection(null);
  };

  const handleCancel = () => {
    setEditingSection(null);
  };

  return (
    <section>
      <DynamicContainer
        main={
          editingSection ? (
            <ProfileEditor
              section={editingSection}
              onCancel={handleCancel}
              onSave={handleSave}
            />
          ) : (
            <ProfileView onEdit={handleEditClick} user={user} />
          )
        }
      />
    </section>
  );
};

export default Profile;