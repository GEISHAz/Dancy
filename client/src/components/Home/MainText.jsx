import React, { useEffect, useRef } from "react";
import { Context, DancyLogo, Text1 } from "./MainText.style";

export default function Main() {
  const ref = useRef();
  const context = useRef();
  const logo = useRef();
	const text1 = useRef();

  useEffect(() => {
    if (ref.current) {
      ref.current.classList.add("show");
    }
  }, []);

  const handleContextLoad = () => {
    if (context.current) {
      context.current.classList.add("show")
    } 
  }

  const handleLogoLoad = () => {
    if (logo.current) {
      logo.current.classList.add("show");
    }
  };

	const handletext1 = () => {
		if (text1.current) {
			text1.current.classList.add("show");
		}
	}

  return (
    <>
      <Context ref={context} src="/src/assets/title.png" onLoad={handleContextLoad} />
      <DancyLogo ref={logo} src="/src/assets/DancyLogo.png" onLoad={handleLogoLoad}/>
			<Text1 ref={text1} src="/src/assets/text1.png" onLoad={handletext1} />
    </>
  )
};
