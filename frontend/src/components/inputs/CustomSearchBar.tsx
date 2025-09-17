import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import style from "./CustomSearchBar.module.css";
import { FaMagnifyingGlass } from "react-icons/fa6";

interface CustomSearchBarProps {
  placeholder?: string;
  onSearch?: (query: string) => void;
  navigateTo?: string;
}

const MagnifyingGlass = FaMagnifyingGlass as unknown as React.FC;

const CustomSearchBar: React.FC<CustomSearchBarProps> = ({
  placeholder = "Buscar...",
  onSearch,
  navigateTo,
}) => {
  const [query, setQuery] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    if (onSearch) {
      onSearch(query);
    }

    navigate(`/${navigateTo}/?search=${encodeURIComponent(query)}`);
  };

  return (
    <form role="search" className={style.searchBar} onSubmit={handleSubmit}>
      <input
        className={style.input}
        type="text"
        value={query}
        placeholder={placeholder}
        onChange={(e) => setQuery(e.target.value)}
      />
      <button type="submit" className={style.iconButton}>
        <MagnifyingGlass />
      </button>
    </form>
  );
};

export default CustomSearchBar;
