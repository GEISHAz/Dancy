import React, { useState, useRef } from "react";
import PlaybackRate from "./PlaybackRate";
import {
  VideoPlayerContainer,
  ControlsWrapper,
  Controls,
  VolumeControl,
  PlayBtn,
  VideoRightOptions,
  FunctionWrapper,
  SaveBtn,
  WithBtn,
  WithArea,
  LikeBtn,
  LikeRate,
} from './VideoPlayer.style';

export default function VideoPlayer({ src }) {
  const videoRef = useRef();
  const [playbackRate, setPlaybackRate] = useState(1);
  const [volume, setVolume] = useState(1);
  const [isPlaying, setIsPlaying] = useState(false);
  const [isMuted, setIsMuted] = useState(false);
  const [isVolumeControlHovered, setIsVolumeControlHovered] = useState(false);
  const [like, setLike] = useState(false);
  const [save, setSave] = useState(false);
  const [likeCount, setLikeCount] = useState(0);

  const handlePlayPause = () => {
    if (videoRef.current.paused) {
      videoRef.current.play();
      setIsPlaying(true);
    } else {
      videoRef.current.pause();
      setIsPlaying(false);
    }
  };

  const handleVolumeChange = (e) => {
    setVolume(e.target.value);
    videoRef.current.volume = volume;
  };

  const handlePlaybackRateChange = (rate) => {
    setPlaybackRate(rate);
    videoRef.current.playbackRate = rate;
  };

  const handleFullScreen = () => {
    if (videoRef.current.requestFullscreen) {
      videoRef.current.requestFullscreen();
    }
  };

  const handleMute = () => {
    setIsMuted(!isMuted);
    if (!isMuted) {
      setVolume(0);
      videoRef.current.volume = 0;
    } else {
      setVolume(1);
      videoRef.current.volume = 1;
    }
  };

  const handleVolumeBtnMouseUp = () => {
    if (isMuted || isVolumeControlHovered) return;
    setIsVolumeControlHovered(false);
  };

  const handleLike = () => {
    setLike(!like);
    if (!like) {
      setLikeCount(likeCount + 1);
    } else {
      setLikeCount(likeCount - 1);
    }
  };

  const handleSave = () => {
    setSave(!save)
  }

  return (
    <>
      <FunctionWrapper>
        <WithArea>
          <WithBtn src="/src/assets/with.png"/>
        </WithArea>
        <SaveBtn src={save ? "/src/assets/saveimage.png" : "/src/assets/unsaveimage.png"} onClick={handleSave}/>
        <LikeBtn src={like ? "/src/assets/likeimage.png" : "/src/assets/unlikeimage.png"} onClick={handleLike} />
        <LikeRate>
          <div>
            {likeCount}
          </div>
        </LikeRate>
      </FunctionWrapper>
      <VideoPlayerContainer>
        <video src={src} ref={videoRef} />
        <ControlsWrapper>
          <Controls>
            <div>
              <PlayBtn onClick={handlePlayPause}>
                <img src={isPlaying ? "/src/assets/pause.png" : "/src/assets/play.png"} alt="재생/일시정지" />
              </PlayBtn>
              <VolumeControl isOpen={!isMuted && isVolumeControlHovered} onMouseEnter={() => setIsVolumeControlHovered(true)} onMouseLeave={() => setIsVolumeControlHovered(false)}>
                <button onClick={handleMute}>
                  <img src={isMuted ? "/src/assets/mute.png" : "/src/assets/volume.png"} alt="볼륨" />
                </button>
                <input
                  type="range"
                  min="0"
                  max="1"
                  step="0.1"
                  value={volume}
                  onChange={handleVolumeChange}
                />
              </VolumeControl>
            </div>
          </Controls>
          <VideoRightOptions>
            <PlaybackRate onChange={handlePlaybackRateChange} />
            <button onClick={handleFullScreen}>
              <img src="/src/assets/fullscreen.png" alt="전체화면" />
            </button>
          </VideoRightOptions>
        </ControlsWrapper>
      </VideoPlayerContainer>
    </>
  );
}

