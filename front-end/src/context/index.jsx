export * from "./data";
import { DataProvider } from "./data";

export const ContextProvider = ({ children }) => {
  return (
    <>
      <DataProvider>
        {children}
      </DataProvider>
    </>
  );
};
