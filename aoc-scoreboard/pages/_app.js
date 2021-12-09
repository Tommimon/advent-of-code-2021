import "tailwindcss/tailwind.css";
import SketchProvider from "../components/context/sketchContext";
import Sketch from "../components/Background";
import Background from "../components/Background";

function MyApp({ Component, pageProps }) {
  return (
    <>
      <SketchProvider>
        <Component {...pageProps} />
        <Background />
      </SketchProvider>
    </>
  );
}

export default MyApp;
