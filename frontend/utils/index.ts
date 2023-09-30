const server_url = "https://hot-stock.shop";
const newsCompanyDict: Record<number, string> = {
  23: "조선일보",
  25: "중앙일보",
  20: "동아일보",
  32: "경향신문",
  28: "한겨레",
  15: "한국경제",
  9: "매일경제",
};

// 랭킹 키워드 호출하기 :: 전체 일괄 호출(현재 순위 25개)
export const fetchKeywords = async () => {
  const res = await fetch(server_url + "/api/keyword", {
    cache: "no-store",
  });
  const data = await res.json();
  return data;
};

// 키워드 상세 조회하기 :: 키워드 아이디로 GET
export const fetchKeywordDetail = async (keyword_id: number) => {
  const res = await fetch(server_url + `/api/keyword/${keyword_id}`, {
    cache: "no-store",
  });
  const data = await res.json();
  return data;
};

export const fetchKeywordDetailForCloud = async (keyword_id: number) => {
  const res = await fetch(server_url + `/api/keyword/${keyword_id}`, {
    cache: "no-store",
  });
  const data = await res.json();
  return data;
};

// 테마에 따른 키워드와 종목 조회하기
export const fetchContentsByTheme = async (theme_id: number) => {
  const res = await fetch(server_url + `/api/theme/${theme_id}`, {
    cache: "no-store",
  });
  const data = await res.json();
  return data;
};

// 종목 상세 조회하기 :: 종목코드로 GET
export const fetchStockDetail = async (code: string) => {
  const res = await fetch(server_url + `/api/stock/${code}`, {
    cache: "no-store",
  });
  const data = await res.json();
  return data;
};

export const fetchStockTheme = async (code: string) => {
  const res = await fetch(server_url + `/api/stocktheme/stock/${code}`, {
    cache: "no-store",
  });
  const data = await res.json();
  return data;
};

export const getNewsByStock = async (search: string, display: number) => {
  const res = await fetch(
    server_url + `/api/news/naver?search=${search}&display=${display}`
  );
  const data = await res.json();
  return data;
};

export const findNewsCompany = (key: number): string => {
  return newsCompanyDict[key];
};

// 테마 다 가져오기
export const fetchThemes = async () => {
  const res = await fetch(server_url + "/api/theme", {
    cache: "no-store",
  });
  const data = await res.json();
  return data;
};

//테마 상세보기
export const fetchThemeDetail = async (theme_id: number) => {
  const res = await fetch(server_url + `/api/stocktheme/theme/${theme_id}`, {
    cache: "no-store",
  });
  const data = await res.json();
  return data;
};
