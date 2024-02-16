import * as P from "./PostModal.style";
import { useNavigate } from "react-router-dom";
import { postArticle } from "../../api/stage";
import { useRef, useState } from "react";
import { useRecoilValue } from "recoil";
import { resultState } from "../../recoil/PracticeState";

export default function PostModal({ getData, videoInfo }) {
  const navigate = useNavigate();
  const data = false;
  const postData = () => {
    getData(data);
  };

	const resultVideo = useRecoilValue(resultState)
  const articleTitleInput = useRef();
  const articleContentInput = useRef();
  const [formData, setFormData] = useState({
    articleTitle: "",
    articleContent: "",
    videoId: resultVideo.videoId,
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handlePost = () => {
    if (formData.articleTitle.length < 1) {
      articleTitleInput.current.focus();
      return;
    }

    if (formData.articleContent.length < 1) {
      articleContentInput.current.focus();
      return;
    }

    console.log(formData)

    postArticle(formData)
    .then ((res) => {
      // console.log(res)
      const articleId = res.articleId
      navigate(`/detail/${articleId}`,  { state: { articleId } })
    })
    .catch ((err) => {
      console.error(err)
    })

  };

  return (
    <P.Modal>
      <P.ModalBackdrop onClick={postData} />

      <P.ModalWrap>
        <P.ModalView>
          <P.BigTitle>POST</P.BigTitle>

          <P.GapRadio>
            <P.Gap>
              <P.Txt>
                <P.InputWrap>
                  <P.Title>제목</P.Title>
                  <P.Input
                    ref={articleTitleInput}
                    name="articleTitle"
                    type="text"
                    value={formData.articleTitle}
                    onChange={handleChange}
                    maxLength={40}
                  />
                </P.InputWrap>

                <P.InputWrap>
                  <P.Title>내용</P.Title>
                  <P.DetailInput
                    ref={articleContentInput}
                    name="articleContent"
                    type="text"
                    value={formData.articleContent}
                    onChange={handleChange}
                  />
                </P.InputWrap>
              </P.Txt>

              {/* <P.InputWrap>
                <P.HashTitle>해시태그</P.HashTitle>
                <P.Input />
              </P.InputWrap> */}
            </P.Gap>

            {/* <P.RadioWrap>
              <P.RadioBox>
                <P.SmallTitle>스켈레톤</P.SmallTitle>
                <P.Radio />
              </P.RadioBox>

              <P.RadioBox>
                <P.SmallTitle>평균 정확도</P.SmallTitle>
                <P.Radio />
              </P.RadioBox>
            </P.RadioWrap> */}
          </P.GapRadio>
        </P.ModalView>

        <P.ModalPost onClick={handlePost}>게시하기</P.ModalPost>
      </P.ModalWrap>
    </P.Modal>
  );
}
