import { useContext } from "react";
import { DataContext } from "../context/admin/adminContext";

export const useData = () => useContext(DataContext);
