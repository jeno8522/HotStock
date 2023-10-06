import { ErrorComp } from "@/components";

const KeywordDetailSubPage = async ({
  params,
}: {
  params: { key: string };
}) => {
  // ----------------------------------------

  return (
    <div className="items-center">
      <ErrorComp />
    </div>
  );
};

export default KeywordDetailSubPage;
