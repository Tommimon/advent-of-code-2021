import React, { useState, useEffect, createContext, useContext } from "react";

export const SketchContext = createContext();

export default function SketchContextComp({ children }) {
  return <SketchContext.Provider>{children}</SketchContext.Provider>;
}
