import React, { useEffect, useRef, useState } from 'react';
import styled from 'styled-components';

const StyledBody = styled.div`
  height: 1000px;
  width: 1000px;
  overflow: hidden;
`;

const SampleCanvas = styled.canvas`
  position: relative;
  top: 65%;
  left: 350px;
  scale: 1.3;
`;


export default function MyComponent() {
  const canvasRef = useRef(null);
  const componentRef = useRef(null);
  const videoImages = useRef([]);
  const totalImagesCount = 114;
  let progress;
  let currentFrame;
  let startScrollPosition = 0;
  const [scrollActive, setScrollActive] = useState(false);

  useEffect(() => {
    const canvas = canvasRef.current;
    const context = canvas.getContext('2d');

    function loadImage(src) {
      return new Promise((resolve, reject) => {
        const img = new Image();
        img.onload = () => resolve(img);
        img.onerror = reject;
        img.src = src;
      });
    }

    async function setImages() {
      try {
        const promises = [];
        for (let i = 0; i < totalImagesCount; i++) {
          promises.push(loadImage(`/src/assets/video/001/${1 + i}.png`));
        }
        const images = await Promise.all(promises);
        videoImages.current = images;
        init();
      } catch (error) {
        console.error('Error loading images:', error);
      }
    }

    function handleScroll() {
      if (!scrollActive) return;
      const scrollableHeight = document.body.offsetHeight - window.innerHeight - startScrollPosition;
      progress = (window.scrollY - startScrollPosition) / (scrollableHeight / (totalImagesCount - 1));
      if (progress < 0) progress = 0;
      if (progress > totalImagesCount - 1) progress = totalImagesCount - 1;
      // console.log(scrollableHeight)
      currentFrame = Math.round(progress);
      if (videoImages.current[currentFrame]) {
        context.clearRect(0, 0, canvas.width, canvas.height);
        context.drawImage(videoImages.current[currentFrame], 0, 0);
      }
    }

    function init() {
      document.body.classList.remove('before-load');
      if (videoImages.current[0]) {
        context.drawImage(videoImages.current[0], 0, 0);
      }
      window.addEventListener('scroll', handleScroll);
      
      const observer = new IntersectionObserver(entries => {
        entries.forEach(entry => {
          if (entry.isIntersecting) {
            startScrollPosition = window.scrollY;
          }
          setScrollActive(entry.isIntersecting);
        });
      });
      observer.observe(componentRef.current);
    }

    setImages();

    return () => {
      window.removeEventListener('scroll', handleScroll);
    };
  }, [scrollActive]);

  return (
    <StyledBody ref={componentRef}>
      <SampleCanvas width="1920" height="1080" ref={canvasRef} />
    </StyledBody>
  );
}
