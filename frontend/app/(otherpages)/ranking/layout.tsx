import type { Metadata } from "next";

interface Props {
  children: React.ReactNode;
}

export const metadata: Metadata = {
  title: "랭킹 - Hot Stock : 키워드로 찾는 주식",
  description:
    "최근 24시간 내 뉴스를 종합한 키워드의 실시간 순위를 보여드려요.",
};

export default function RankingLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      <div>
        <div>{children}</div>
      </div>
    </>
  );
}
