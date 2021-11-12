// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import type { NextApiRequest, NextApiResponse } from "next";
import { prisma } from "../../../lib/db";

export default async function handler(
  req: NextApiRequest,
  res: NextApiResponse<string>
) {
  if (req.method === "DELETE") {
    const id = req.query.id as string;
    console.log(id);

    await prisma.todo.delete({
      where: { id: +id },
    });

    res.status(200).json("success");
  }
}
