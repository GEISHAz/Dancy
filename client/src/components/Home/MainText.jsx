import React, { useEffect, useRef } from "react";
import MyComponent from "./Main"
import * as M from "./MainText.style";

export default function Main() {
  const ref = useRef();
  const context = useRef();
  const logo = useRef();
	const text1 = useRef();
  const text2 = useRef();
  const text3 = useRef();

  useEffect(() => {
    if (ref.current) {
      ref.current.classList.add("show");
      ref.current.classList.add("moveRight");
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

  const handletext2 = () => {
    if (text2.current) {
      text2.current.classList.add("show");
    }
  }

  const handletext3 = () => {
    if (text3.current) {
      text3.current.classList.add("show");
    }
  }

  return (
    <M.Wrapper>
      <M.Container>
        <M.Context ref={context} src="/src/assets/title.png" onLoad={handleContextLoad} />
        <M.DancyLogo ref={logo} src="/src/assets/DancyLogo.png" onLoad={handleLogoLoad}/>
        <M.Text1 ref={text1} src="/src/assets/text1.png" onLoad={handletext1} />
        <M.Text2 ref={text2} src="/src/assets/Homepage/1.png" onLoad={handletext2}/>
      </M.Container>
      <div>
        <MyComponent ref={ref} />
        <M.Text3 ref={text3} src="/src/assets/Homepage/text1.png" onLoad={handletext3} />
      </div>
    </M.Wrapper>
  )
};
