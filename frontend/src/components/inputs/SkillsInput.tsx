import { useState } from "react";
import style from "./SkillsInput.module.css";
import SkillTag from "../buttons/SkillTag";

// Example skills for suggestion
const allSkills = [
  "JavaScript",
  "TypeScript",
  "React",
  "HTML",
  "CSS",
  "Python",
  "Node",
  "Git",
  "Docker",
  "Kubernetes",
  "PostgreSQL",
  "MongoDB",
  "MySQL",
  "Figma",
  "Jira",
];

interface SkillsInputProps {
  selectedSkills: string[];
  onChange: (skills: string[]) => void;
  placeholder?: string;
}

const SkillsInput = ({
  selectedSkills,
  onChange,
  placeholder = "Escribe una habilidad...",
}: SkillsInputProps) => {
  const [inputValue, setInputValue] = useState("");
  const [activeIndex, setActiveIndex] = useState(-1); // for arrow navigation

  const filteredSuggestions = allSkills.filter(
    (skill) =>
      skill.toLowerCase().includes(inputValue.toLowerCase()) &&
      !selectedSkills.includes(skill)
  );

  const handleAddSkill = (skill: string) => {
    const normalized =
      allSkills.find((s) => s.toLowerCase() === skill.toLowerCase()) || skill;
    onChange([...selectedSkills, normalized]);
    setInputValue("");
    setActiveIndex(-1);
  };

  const handleRemoveSkill = (skill: string) => {
    onChange(selectedSkills.filter((s) => s !== skill));
  };

  const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === "ArrowDown") {
      e.preventDefault();
      setActiveIndex((prev) =>
        prev < filteredSuggestions.length - 1 ? prev + 1 : 0
      );
    } else if (e.key === "ArrowUp") {
      e.preventDefault();
      setActiveIndex((prev) =>
        prev > 0 ? prev - 1 : filteredSuggestions.length - 1
      );
    } else if (e.key === "Enter" && inputValue.trim()) {
      e.preventDefault();
      if (activeIndex >= 0) {
        handleAddSkill(filteredSuggestions[activeIndex]);
      } else {
        handleAddSkill(inputValue.trim());
      }
    } else if (e.key === "Escape") {
      setActiveIndex(-1);
    }
  };

  return (
    <>
      <label className={style.label}>Habilidades</label>
      <div className={style.wrapper}>
        <div className={style.inputWrapper}>
          <input
            type="text"
            className={style.input}
            placeholder={placeholder}
            value={inputValue}
            onChange={(e) => setInputValue(e.target.value)}
            onKeyDown={handleKeyDown}
            style={
              inputValue.trim().length > 0 && filteredSuggestions.length > 0
                ? { borderRadius: "12px 12px 0 0", borderBottom: "none" }
                : {}
            }
          />
          {inputValue.trim().length > 0 && filteredSuggestions.length > 0 && (
            <ul className={style.suggestions} role="listbox">
              {filteredSuggestions.map((skill, index) => (
                <li
                  key={skill}
                  role="option"
                  className={`${style.suggestionItem} ${
                    index === activeIndex ? style.activeSuggestion : ""
                  }`}
                  onClick={() => handleAddSkill(skill)}
                  onMouseEnter={() => setActiveIndex(index)}
                >
                  {skill}
                </li>
              ))}
            </ul>
          )}
        </div>

        <div className={style.tags}>
          {selectedSkills.map((skill) => (
            <SkillTag
              key={skill}
              skill={skill}
              onRemove={() => handleRemoveSkill(skill)}
            />
          ))}
        </div>
      </div>
    </>
  );
};

export default SkillsInput;
