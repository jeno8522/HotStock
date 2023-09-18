import localfont from "next/font/local";

interface Props {
  children: React.ReactNode;
}

export default function KeywordDetailLayout({
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
