export * from "./auth";
export * from "./contact";
export * from "./admin";
export * from "./info";
export * from "./student";

import { AuthProvider } from "./auth";
import { ContactProvider } from "./contact";
import { AdminProvider } from "./admin";
import { InfoProvider } from "./info";
import { StudentProvider } from "./student";

export const ContextProvider = ({ children }) => {
  return (
    <>
      <InfoProvider>
        <AuthProvider>
          <AdminProvider>
            <StudentProvider>
              {children}
            </StudentProvider>
          </AdminProvider>
        </AuthProvider>
      </InfoProvider>
    </>
  );
};
