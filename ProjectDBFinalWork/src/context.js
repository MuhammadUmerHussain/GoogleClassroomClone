import React, { useState, useContext, useEffect } from "react";

import data from "./components/data";

const AppContext = React.createContext();

const AppProvider = ({ children }) => {
  const [assignId, setassignId] = useState("");
  const [navbarb, setnavbarb] = useState(false);


    
  const InitialSession = {
    name: "",
    password: "",
  };
  const [isteacher, setisteacher] = useState(true);
  const [clickpeople, setclickpeople] = useState(false);
  const [classid, setclassid] = useState("");
  const [Session, SetSession] = useState(InitialSession);
  const [loginstate, setloginstate] = useState(false);

  const [issidebaropen, setissidebaropen] = useState(false);

  const [loading, setloading] = useState(false);

  const opensidebar = () => {
    setissidebaropen(true);
  };

  const fetchData = async () => {
    try {
      setloading(true);

      if (issidebaropen) {
      } else {
      }
      setloading(false);
    } catch (error) {}
  };
  const [isclasswork, setclasswork] = useState(false);
  const closedsidebar = () => {
    setissidebaropen(false);
  };
  return (
    <AppContext.Provider
      value={{
        Session,
        SetSession,
        navbarb,
        setnavbarb,
        issidebaropen,
        opensidebar,
        closedsidebar,
        setissidebaropen,
        classid,
        setclassid,
        setclickpeople,
        clickpeople,
        isclasswork,
        setclasswork,
        loginstate,
        setloginstate,
        assignId,
        setassignId,
        isteacher,
        setisteacher,
      }}
    >
      {children}
    </AppContext.Provider>
  );
};

export const useGlobalContext = () => {
  return useContext(AppContext);
};

export { AppContext, AppProvider };
