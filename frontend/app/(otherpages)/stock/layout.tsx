import localfont from "next/font/local";

interface Props {
  children: React.ReactNode;
}

const cafe24SsurroundAir = localfont({
  src: [
    {
      path: "../../../public/fonts/cafe24SsurroundAir.ttf",
      weight: "normal",
      style: "normal",
    },
  ],
});

export default function KeywordDetailLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      <div className={cafe24SsurroundAir.className}>
        <div>{children}</div>
      </div>
    </>
  );
}
