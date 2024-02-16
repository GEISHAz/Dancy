import * as P from "./UpdateModal.style";
import { useNavigate } from "react-router-dom";
import { postArticle } from "../../api/stage";
import { useRef, useState } from "react";
import { UpdateArticle } from "../../api/stage";

export default function PostModal({ articleId, beforeData, getData }) {
  const navigate = useNavigate();
  const data = false;
  const postData = () => {
    getData(data);
  };

  const articleTitleInput = useRef();
  const articleContentInput = useRef();
  const [formData, setFormData] = useState({
    articleTitle: beforeData.articleTitle,
    articleContent: beforeData.articleContent,
    video: beforeData.video,
    thumbnailImageUrl: beforeData.thumbnailImageUrl,
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleUpdate = () => {
    if (formData.articleTitle.length < 1) {
      articleTitleInput.current.focus();
      return;
    }

    if (formData.articleContent.length < 1) {
      articleContentInput.current.focus();
      return;
    }
    console.log(articleId);
    UpdateArticle({ articleId, formData })
      .then((res) => {
        console.log(res);
        window.location.reload();
      })
      .catch((err) => {
        console.error(err);
      });
  };

  return (
    <P.Modal>
      <P.ModalBackdrop onClick={postData} />

      <P.ModalWrap>
        <P.ModalView>
          <P.BigTitle>UPDATE</P.BigTitle>

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

        <P.ModalPost onClick={handleUpdate}>수정하기</P.ModalPost>
      </P.ModalWrap>
    </P.Modal>
  );
}
