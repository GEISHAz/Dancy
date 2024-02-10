import { atom } from 'recoil';

export const searchKeywordState = atom({
  key: "searchKeywordState",
  default: ''
});

export const searchResultsState = atom({
  key: "searchResultsState",
  default: []
});