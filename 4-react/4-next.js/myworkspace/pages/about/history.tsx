import Layout from "../../components/layout";
import Sidebar from "../../components/about/sidebar";

export default function History() {
  return (
    <Layout>
      <article className="d-flex">
        <Sidebar />
        <section>
          <p>History Page</p>
        </section>
      </article>
    </Layout>
  );
}
