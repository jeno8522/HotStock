// 랭킹 키워드 호출하기 :: 전체 일괄 호출(현재 순위 25개)
export const fetchKeywords = async () => {
  // const res = await fetch("https://j9a206.p.ssafy.io/api/keyword");
  const res = await fetch("http://localhost:8080/api/keyword", {
    cache: "no-store",
  });
  const data = await res.json();
  return data;
};

// 키워드 상세 조회하기 :: 키워드 아이디로 GET
export const fetchKeywordDetail = async (keyword_id: number) => {
  // const res = await fetch(`https://j9a206.p.ssafy.io/api/keyword/${keyword_id}`);
  const res = await fetch(`http://localhost:8080/api/keyword/${keyword_id}`, {
    cache: "no-store",
  });
  const data = await res.json();
  return data;
};

// 테마에 따른 키워드와 종목 조회하기
export const fetchContentsByTheme = async (theme_id: number) => {
  const res = await fetch(`http://localhost:8080/api/theme/${theme_id}`);
  const data = await res.json();
  return data;
};

// 종목 상세 조회하기 :: 종목코드로 GET
export const fetchStockDetail = async (code: string) => {
  const res = await fetch("url", {
    cache: "no-store",
  });
  const data = await res.json();
  return data;
};

// 종목 검색하기 :: 종목 명 혹은 종목 코드 상관없이 string으로 전송
export const searchStocks = async (searchInput: string) => {
  const res = await fetch("url");
  const data = await res.json();
  return data;
};

export const findNewsCompany = (key: number) => {
  let company = "";
  if (key == 23) company = "조선일보";
  if (key == 25) company = "중앙일보";
  if (key == 20) company = "동아일보";
  if (key == 32) company = "경향신문";
  if (key == 28) company = "한겨레";
  if (key == 15) company = "한국경제";
  if (key == 9) company = "매일경제";
  return company;
};
